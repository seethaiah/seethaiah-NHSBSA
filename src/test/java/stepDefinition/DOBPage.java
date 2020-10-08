package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Given;
import pageObject.DOBPagePO;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class DOBPage  {
	
	/** The driver. */
	WebDriver driver = Hooks.driver;

	
	/**
	 * User enters date of birth as.
	 *
	 * @param day the day
	 * @param month the month
	 * @param year the year
	 * @throws Throwable the throwable
	 */
	@Given("^User enters Date of Birth as \"([^\"]*)\", \"([^\"]*)\" ,\"([^\"]*)\"$")
	public void user_enters_Date_of_Birth_as(String day, String month, String year) throws Throwable {
		DOBPagePO dobPagePO = PageFactory.instantiatePage(driver, DOBPagePO.class);
		dobPagePO.enterDOB(day,month,year);
	}


}
