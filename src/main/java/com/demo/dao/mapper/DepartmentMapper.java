package com.demo.dao.mapper;

import com.demo.entity.Department;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * ResultSet to object mapper.
 */
public class DepartmentMapper implements RowMapper<Department> {

  @Override
  public Department map(ResultSet rs, StatementContext ctx) throws SQLException {

    Department department = new Department();

    department.setId(rs.getLong("ID"));
    department.setName(rs.getString("NAME"));
    department.setActive(rs.getString("ACTIVE"));

    return department;
  }
}
