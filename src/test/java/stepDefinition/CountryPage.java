package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Given;
import pageObject.CountryPagePO;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class CountryPage  {
	
	/** The driver. */
	WebDriver driver = Hooks.driver;

	
	@Given("^User on Where You Live Page with title \"([^\"]*)\"$")
	public void user_on_Where_You_Live_Page_with_title(String arg1) throws Throwable {
		CountryPagePO countryPagePO = PageFactory.instantiatePage(driver, CountryPagePO.class);
		countryPagePO.getTitle();
	}
	
	@Given("^User Selects \"([^\"]*)\" Radio button from the list$")
	public void user_Selects_Radio_button_from_the_list(String arg1) throws Throwable {
		CountryPagePO countryPagePO = PageFactory.instantiatePage(driver, CountryPagePO.class);
		countryPagePO.selectRadioButton();
	}

	@Given("^User clicks Next button$")
	public void user_clicks_Next_button() throws Throwable {
		CountryPagePO countryPagePO = PageFactory.instantiatePage(driver, CountryPagePO.class);
		countryPagePO.clickNextButton();
	}
}
