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

	
	/**
	 * User on where you live page with title.
	 *
	 * @param arg1 the arg 1
	 * @throws Throwable the throwable
	 */
	@Given("^User on Where You Live Page with title \"([^\"]*)\"$")
	public void user_on_Where_You_Live_Page_with_title(String arg1) throws Throwable {
		CountryPagePO countryPagePO = PageFactory.instantiatePage(driver, CountryPagePO.class);
		countryPagePO.getTitle();
	}
	
	/**
	 * User selects radio button from the list.
	 *
	 * @param arg1 the arg 1
	 * @throws Throwable the throwable
	 */
	@Given("^User Selects \"([^\"]*)\" Radio button from the list$")
	public void user_Selects_Radio_button_from_the_list(String arg1) throws Throwable {
		CountryPagePO countryPagePO = PageFactory.instantiatePage(driver, CountryPagePO.class);
		countryPagePO.selectRadioButton();
	}

	/**
	 * User clicks next button.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^User clicks Next button$")
	public void user_clicks_Next_button() throws Throwable {
		CountryPagePO countryPagePO = PageFactory.instantiatePage(driver, CountryPagePO.class);
		countryPagePO.clickNextButton();
	}
}
