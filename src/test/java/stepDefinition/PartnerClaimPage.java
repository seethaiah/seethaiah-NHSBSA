package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Given;
import pageObject.PartnerClaimPO;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class PartnerClaimPage  {
	
	/** The driver. */
	WebDriver driver = Hooks.driver;

	
/**
 * User selects the yes or no radio button in partner claim benefit page.
 *
 * @param selection the selection
 * @throws Throwable the throwable
 */
/*	@Given("^User enters Date of Birth as \"([^\"]*)\", \"([^\"]*)\" ,\"([^\"]*)\"$")
	public void user_enters_Date_of_Birth_as(String day, String month, String year) throws Throwable {
		DOBPagePO dobPagePO = PageFactory.instantiatePage(driver, DOBPagePO.class);
		dobPagePO.enterDOB(day,month,year);
	}
*/
	@Given("^User selects the Yes or No radio button in PartnerClaimBenefit Page \"([^\"]*)\"$")
	public void user_selects_the_Yes_or_No_radio_button_in_PartnerClaimBenefit_Page(String selection) throws Throwable {
		PartnerClaimPO partnerClaimPO = PageFactory.instantiatePage(driver, PartnerClaimPO.class);
		partnerClaimPO.selectOption(selection);
	}

}
