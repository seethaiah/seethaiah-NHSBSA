package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Given;
import pageObject.CombinedTakeHomePO;

/**
 * The Class HomePage.
 */
public class CombinedTakeHomePage {

	/** The driver. */
	WebDriver driver = Hooks.driver;

	/**
	 * User selects yes or no radio button in combined home pay page.
	 *
	 * @param selection the selection
	 * @throws Throwable the throwable
	 */
	@Given("^User selects Yes or No radio button in CombinedHomePay Page \"([^\"]*)\"$")
	public void user_selects_Yes_or_No_radio_button_in_CombinedHomePay_Page(String selection) throws Throwable {
		CombinedTakeHomePO combinedTakeHomePO = PageFactory.instantiatePage(driver, CombinedTakeHomePO.class);
		combinedTakeHomePO.selectOption(selection);
	}
}
