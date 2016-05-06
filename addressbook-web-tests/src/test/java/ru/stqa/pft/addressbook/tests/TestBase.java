package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by irinagavrilova on 4/17/16.
 */
public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);


  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }


  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }
}
