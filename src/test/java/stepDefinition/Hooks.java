package stepDefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.CucumberWebBase;
import com.seeta.common.framework.cucumber.web.core.EnvParameters;
import com.seeta.common.framework.cucumber.web.core.LoggerUtil;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

// TODO: Auto-generated Javadoc
/**
 * The Class Hooks.
 */
public class Hooks extends CucumberWebBase {

  /** The driver. */
  static WebDriver driver;

  /**
   * Gets the driver.
   *
   * @param scenario the scenario
   * @return the driver
   */
  @Before
  public static WebDriver getDriver(Scenario scenario) {
    LoggerUtil.log("Starting Scenario " + scenario.getName());
    LoggerUtil.log("**************************************************************************************************");
    return driver = CucumberWebBase.getDriver(scenario);
  }

  /**
   * Execute after scenario.
   *
   * @param scenario the scenario
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @After
  public void executeAfterScenario(Scenario scenario) throws IOException {
      String[] array = scenario.getName().split("#");
      String scenarioName = null;
      String testId = null;
      String result = null;
      String screenshotPath = null;
      if (array.length > 0) {
      scenarioName = array[0];
      testId = array[0].trim();
      } else {
    	  scenarioName = scenario.getName();
      }
	  if (scenario.isFailed() && EnvParameters.CAPTURE_SCREENSHOT) {
      File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      screenshotPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "screenshots" + File.separator
              + scenarioName + ".png";
      FileUtils.copyFile(src, new File(screenshotPath));
    }
	 if (scenario.isFailed()) {
		 result = "Failed";
	 } else {
		 result = "Passed";
	 }
    quitBrowser();
    LoggerUtil.log("Ending Scenario " + scenario.getName());
    LoggerUtil.log("**************************************************************************************************");   
  }
}
