package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Given;
import pageObject.JointUniversalCreditClaimPO;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class JointUniversalCreditClaimPage  {
	
	/** The driver. */
	WebDriver driver = Hooks.driver;

/*	
	@Given("^User enters Date of Birth as \"([^\"]*)\", \"([^\"]*)\" ,\"([^\"]*)\"$")
	public void user_enters_Date_of_Birth_as(String day, String month, String year) throws Throwable {
		DOBPagePO dobPagePO = PageFactory.instantiatePage(driver, DOBPagePO.class);
		dobPagePO.enterDOB(day,month,year);
	}
*/
	
	/**
 * User selects the yes or no radio button in universal credit claim page.
 *
 * @param selection the selection
 * @throws Throwable the throwable
 */
@Given("^User selects Yes or No radio button in JointUniversalCredit Page \"([^\"]*)\"$")
	public void user_selects_the_Yes_or_No_radio_button_in_UniversalCreditClaim_Page(String selection) throws Throwable {
		JointUniversalCreditClaimPO jointUniversalCreditClaimPO = PageFactory.instantiatePage(driver, JointUniversalCreditClaimPO.class);
		jointUniversalCreditClaimPO.selectOption(selection);
	}

}
