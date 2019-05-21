package com.demo.test.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.demo.dao.DepartmentDao;
import com.demo.entity.Department;
import com.demo.test.support.AbstractPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class DepartmentDaoTest extends AbstractPersistenceTest {

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


}
