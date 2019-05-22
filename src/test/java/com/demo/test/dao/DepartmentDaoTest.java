package com.demo.test.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.demo.dao.DepartmentDao;
import com.demo.entity.Department;
import com.demo.test.support.AbstractPersistenceTest;
import org.jdbi.v3.core.Handle;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentDaoTest extends AbstractPersistenceTest {
  private static Logger logger = LoggerFactory.getLogger(DepartmentDaoTest.class);


  private DepartmentDao dao;


  @Before
  public void before() {
    dao = getHandle().attach(DepartmentDao.class);
  }


  @Test
  public void testReadById_recordExists() {
    final Long departmentId = 1L;

    final Long expectedDepartmentId = 1L;
    final String expectedDepartmentName = "HR";
    final String expectedDepartmentActive = "Y";

    Department department = dao.readById(departmentId);
    assertNotNull(department);

    assertThat(department.getId(), equalTo(expectedDepartmentId));
    assertThat(department.getName(), equalTo(expectedDepartmentName));
    assertThat(department.getActive(), equalTo(expectedDepartmentActive));
  }

  /**
   * Returns null when row by id does not exist.
   */
  @Test
  public void testReadById_recordDoesNotExist() {
    final Long departmentId = 2001L;

    Department department = dao.readById(departmentId);
    assertNull(department);
  }

  /**
   * Returns "true" when update is successful.
   */
  @Test
  public void testUpdate_whenValidRecord() {

    final Handle handle = getHandle();

    final Long departmentId = 1L;
    final String originalDepartmentName = "HR";
    final String newDepartmentName = "Sciences";

    Department department =
        handle.inTransaction(h -> h.attach(DepartmentDao.class).readById(departmentId));
    assertNotNull(department);
    assertThat(department.getName(), equalTo(originalDepartmentName));
    // change to Sciences
    department.setName(newDepartmentName);

    boolean isSuccess =
        handle.inTransaction(h -> h.attach(DepartmentDao.class).update(department));
    assertTrue(isSuccess);

    Department updatedDepartment =
        handle.inTransaction(h -> h.attach(DepartmentDao.class).readById(departmentId));
    assertNotNull(updatedDepartment);
    assertThat(updatedDepartment.getName(), equalTo(newDepartmentName));
  }

  @Test
  public void testUpdate_whenRecordNotExist() {
    final Long badDepartmentId = 99999999999L;

    final Department badDeparment = new Department();
    badDeparment.setId(badDepartmentId);
    badDeparment.setName("BLAH");
    badDeparment.setActive("N");

    boolean isSuccess = dao.update(badDeparment);
    assertFalse(isSuccess);

  }


}
