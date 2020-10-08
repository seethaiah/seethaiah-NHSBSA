package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class PartnerPagePO  extends WebDriverBase{
	
	/** The sign in link. */
	private static String PYesRadioButton = getLocator("PYesRadioButton");
	
	/** The P no radio button. */
	private static String PNoRadioButton = getLocator("PNoRadioButton");

	
  /**
   * Enter DOB.
   *
   * @param partner the partner
   */
  public void selectRadioButton(String partner) {
		 waitForPageToLoad();
		 String elementToSelect= "";
		 if (partner.equalsIgnoreCase("Yes")) {
			  elementToSelect = PYesRadioButton;
		 }
		 else {
			 elementToSelect = PNoRadioButton;
		 }
	        wait(Until.elementsToBePresent(elementToSelect));
			clickOnElement(elementToSelect);
	       
  }
	
	
}
