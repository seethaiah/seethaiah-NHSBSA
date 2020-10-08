package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import org.openqa.selenium.By;

import com.seeta.common.framework.cucumber.web.core.ObjectLocators;
import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
/**
 * The Class HomePagePO.
 */
public class DOBPagePO  extends WebDriverBase{
	
	/** The sign in link. */
	private static String DDayInputBox = getLocator("DDayInputBox");
	
	/** The D month input box. */
	private static String DMonthInputBox = getLocator("DMonthInputBox");
	
	/** The D year input box. */
	private static String DYearInputBox = getLocator("DYearInputBox");
	
	/**
	 * Enter DOB.
	 *
	 * @param day the day
	 * @param month the month
	 * @param year the year
	 */
	public void enterDOB(String day, String month, String year) {
		 waitForPageToLoad();
	        wait(Until.elementsToBePresent(DDayInputBox));
			clickOnElement(DDayInputBox);
	       findElement(DDayInputBox).sendKeys(day);
	       findElement(DMonthInputBox).sendKeys(month);
	       findElement(DYearInputBox).sendKeys(year);
  }
	
	
}
