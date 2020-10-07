package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import org.openqa.selenium.By;

import com.seeta.common.framework.cucumber.web.core.ObjectLocators;
import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class DOBPagePO  extends WebDriverBase{
	
	/** The sign in link. */
	private static String DDayInputBox = getLocator("DDayInputBox");
	private static String DMonthInputBox = getLocator("DMonthInputBox");
	private static String DYearInputBox = getLocator("DYearInputBox");
	public void enterDOB(String day, String month, String year) {
		 waitForPageToLoad();
	        wait(Until.elementsToBePresent(DDayInputBox));
			clickOnElement(DDayInputBox);
	        By locator1 = ObjectLocators.getBySelector(DDayInputBox);
			driver.findElement(locator1).sendKeys(day);
			clickOnElement(DDayInputBox);
	        By locator2 = ObjectLocators.getBySelector(DMonthInputBox);
	        driver.findElement(locator2).sendKeys(month);
			clickOnElement(DDayInputBox);
	        By locator3 = ObjectLocators.getBySelector(DYearInputBox);
	        driver.findElement(locator3).sendKeys(year);
  }
	
	
}
