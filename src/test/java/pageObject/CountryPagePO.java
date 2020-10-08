package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
import static org.junit.Assert.assertTrue;
// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class CountryPagePO  extends WebDriverBase{
	
	/** The sign in link. */
	private static String CRadioButton = getLocator("CRadioButton");
	
	/** The C next button. */
	private static String CNextButton = getLocator("CNextButton");

	

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public void getTitle() {
		waitForPageToLoad();
		assertTrue(driver.getTitle().contains("Which country do you live in?"));
	}

	
	/**
	 * Click on start button.
	 */
	public void selectRadioButton() {
        waitForPageToLoad();
		  wait(Until.elementsToBePresent(CRadioButton));
			clickOnElement(CRadioButton);
	      
	}
	
	/**
	 * Click next button.
	 */
	public void clickNextButton() {
		waitForPageToLoad();
		  wait(Until.elementsToBePresent(CNextButton));
			clickOnElement(CNextButton);
	}
}
