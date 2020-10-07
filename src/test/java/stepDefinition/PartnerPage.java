package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Given;
import pageObject.PartnerPagePO;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class PartnerPage  {
	
	/** The driver. */
	WebDriver driver = Hooks.driver;

	
	@Given("^User selects the Yes or No radio button as \"([^\"]*)\"$")
	public void user_selects_the_Yes_or_No_radio_button_for_as(String partner) throws Throwable {
		PartnerPagePO partnerPagePO = PageFactory.instantiatePage(driver, PartnerPagePO.class);
		partnerPagePO.selectRadioButton(partner);
	
	}

}
