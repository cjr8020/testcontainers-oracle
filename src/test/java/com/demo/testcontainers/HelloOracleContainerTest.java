package com.demo.testcontainers;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.testcontainers.containers.OracleContainer;

@RunWith(MockitoJUnitRunner.class)
public class HelloOracleContainerTest {

  @Rule
  public OracleContainer oracle = new OracleContainer("oracleinanutshell/oracle-xe-11g");

  @Test
  public void someTestMethod() throws SQLException {
    String testQueryString = "SELECT 1 FROM DUAL";

    String jdbcUrl = oracle.getJdbcUrl();
    String username = oracle.getUsername();
    String password = oracle.getPassword();
    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
    ResultSet resultSet = connection.createStatement().executeQuery(testQueryString);
    resultSet.next();
    int result = resultSet.getInt(1);

    assertEquals(1, result);

  }

}
