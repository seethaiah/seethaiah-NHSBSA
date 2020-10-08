package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class UniversalCreditClaimPO  extends WebDriverBase{
	
/** The UC yes radio button. */
private static String UCYesRadioButton = getLocator("UCYesRadioButton");
	
	/** The PC no radio button. */
	private static String UCNoRadioButton = getLocator("UCNoRadioButton");
	
	/**
	 * Select option.
	 *
	 * @param selection the selection
	 */
	public void selectOption(String selection) {
		
				 waitForPageToLoad();
				 String elementToSelect= "";
				 if (selection.equalsIgnoreCase("Yes")) {
					  elementToSelect = UCYesRadioButton;
				 }
				 else {
					 elementToSelect = UCNoRadioButton;
				 }
			        wait(Until.elementsToBePresent(elementToSelect));
					clickOnElement(elementToSelect);
			       
		  }
	
}
