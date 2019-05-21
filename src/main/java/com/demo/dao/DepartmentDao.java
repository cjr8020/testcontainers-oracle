package com.demo.dao;

import com.demo.dao.mapper.DepartmentMapper;
import com.demo.entity.Department;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.stringtemplate4.UseStringTemplateSqlLocator;

@RegisterRowMapper(DepartmentMapper.class)
@UseStringTemplateSqlLocator
public interface DepartmentDao {

  @SqlQuery
  Department readById(@Bind("id") Long id);

  @SqlQuery
  Long selectDepartmentSeqNextVal();

  @SqlQuery
  Long selectDepartmentSeqCurrVal();

  @SqlUpdate
  boolean update(@BindBean Department department);
}
