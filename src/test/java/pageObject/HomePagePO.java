package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class HomePagePO  extends WebDriverBase{
	
	/** The sign in link. */
	private static String HstartButton = getLocator("HstartButton");

	
	
	
	/**
	 * Gets the home page.
	 *
	 * @param url the url
	 * @return the home page
	 */
	public void getHomePage(String url) {
		waitForPageToLoad();
		goToUrl(url);
	}
	
	
	/**
	 * Click on start button.
	 */
	public void clickOnStartButton() {
        waitForPageToLoad();
		  wait(Until.elementsToBePresent(HstartButton));
			clickOnElement(HstartButton);
	      
	}
}
