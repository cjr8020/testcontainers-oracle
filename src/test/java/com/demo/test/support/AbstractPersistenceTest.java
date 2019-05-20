package com.demo.test.support;

import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.sql.DataSource;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import oracle.jdbc.pool.OracleDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.OracleContainer;

public class AbstractPersistenceTest extends BaseTest {
  private static Logger logger = LoggerFactory.getLogger(AbstractPersistenceTest.class);

  @ClassRule
  public static OracleContainer oracleContainer;

  private Liquibase liquibase;


  @BeforeClass
  public static void beforeClass() {

    logger.info("Starting Oracle Container ... {}", LocalDateTime.now());
    oracleContainer = new OracleContainer();
    oracleContainer.start();
    logger.info("Oracle Container started. {}", LocalDateTime.now());
  }

  @Before
  public void before() throws SQLException, LiquibaseException {
    // execute test DB migration
    migrateDatabase();
  }

  @After
  public void after() throws DatabaseException {
    liquibase.dropAll();
  }

  @AfterClass
  public static void afterClass() {
    logger.info("Stopping Oracle Container ... {}", LocalDateTime.now());
    oracleContainer.stop();
    logger.info("Oracle Container stopped. {}", LocalDateTime.now());
  }

  /**
   * NOTE: all properties are prefixed with "jdbc".  This prefix identifies
   * DataSource properties for DB connections to the SIC schema.
   *
   * @return DataSourceFactory
   */
  private DataSource getTestDataSource() throws SQLException {

    OracleDataSource oracleDataSource = new OracleDataSource();
    oracleDataSource.setURL(oracleContainer.getJdbcUrl());
    oracleDataSource.setUser(oracleContainer.getUsername());
    oracleDataSource.setPassword(oracleContainer.getPassword());

    return oracleDataSource;
  }

  private void migrateDatabase() throws SQLException, LiquibaseException {

    Database database = DatabaseFactory.getInstance()
        .findCorrectDatabaseImplementation(
            new JdbcConnection(getTestDataSource().getConnection()));

    liquibase = new liquibase.Liquibase(
        "db/changelog/db.changelog-master-test.yaml",
        new ClassLoaderResourceAccessor(),
        database);

    liquibase.update(new Contexts(), new LabelExpression());
  }

}
