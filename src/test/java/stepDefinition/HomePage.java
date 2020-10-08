package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.seeta.common.framework.cucumber.web.core.PageFactory;

import cucumber.api.java.en.Given;
import pageObject.HomePagePO;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class HomePage  {
	
	/** The driver. */
	WebDriver driver = Hooks.driver;

	/**
	 * User is on homepage.
	 *
	 * @param url the url
	 * @throws Throwable the throwable
	 */
	@Given("^User is on Homepage \"([^\"]*)\"$")
	public void user_is_on_Homepage(String url) throws Throwable {
		HomePagePO homePagePO = PageFactory.instantiatePage(driver, HomePagePO.class);
		homePagePO.getHomePage(url);
	}

	/**
	 * User clicks on start button.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^User clicks on Start Button$")
	public void user_clicks_on_Start_Button() throws Throwable {
		HomePagePO homePagePO = PageFactory.instantiatePage(driver, HomePagePO.class);
		homePagePO.clickOnStartButton();
	}

/*	


	

	
	@Given("^User selects the Yes or No radio button for \"([^\"]*)\"$")
	public void user_selects_the_Yes_or_No_radio_button_for(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^User selects Yes or No radio button for \"([^\"]*)\"$")
	public void user_selects_Yes_or_No_radio_button_for(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^User clicks Next button$")
	public void user_clicks_Next_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^User eligbility will be displayed$")
	public void user_eligbility_will_be_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
*/
}
