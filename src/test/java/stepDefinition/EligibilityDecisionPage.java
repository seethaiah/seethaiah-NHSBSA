package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Then;
import pageObject.EligibilityDecisionPO;

/**
 * The Class HomePage.
 */
public class EligibilityDecisionPage {

	/** The driver. */
	WebDriver driver = Hooks.driver;

	/**
	 * User selects yes or no radio button in combined home pay page.
	 *
	 * @param selection the selection
	 * @throws Throwable the throwable
	 */
	/*@Given("^User selects Yes or No radio button in CombinedHomePay Page \"([^\"]*)\"$")
	public void user_selects_Yes_or_No_radio_button_in_CombinedHomePay_Page(String selection) throws Throwable {
		CombinedTakeHomePO combinedTakeHomePO = PageFactory.instantiatePage(driver, CombinedTakeHomePO.class);
		combinedTakeHomePO.selectOption(selection);
	}*/
	
	@Then("^User Eligibility is checked and successfull message displayed as \"([^\"]*)\"$")
	public void user_Eligibility_is_checked_and_successfull_message_displayed_as(String message) throws Throwable {
		EligibilityDecisionPO eligibilityDecisionPO = PageFactory.instantiatePage(driver, EligibilityDecisionPO.class);
		eligibilityDecisionPO.getMessage(message);
	}
}
