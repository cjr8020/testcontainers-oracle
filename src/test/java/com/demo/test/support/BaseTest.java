package com.demo.test.support;

import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
  private static Logger lggger = LoggerFactory.getLogger(BaseTest.class);

  @Rule
  public TestWatcher testWatcher = new TestWatcher() {
    public void starting(Description description) {
      BaseTest.lggger.info(" *** Executing Test: {} ...", description.getDisplayName());
    }

    public void skipped(AssumptionViolatedException exception, Description description) {
      BaseTest.lggger.info(" -- Test {} was skipped...", description.getDisplayName());
    }
  };

  public BaseTest() {
  }
}
