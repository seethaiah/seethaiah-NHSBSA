package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class CombinedTakeHomePO  extends WebDriverBase{
	
/** The CT yes radio button. */
private static String CTYesRadioButton = getLocator("CTYesRadioButton");
	
	/** The PC no radio button. */
	private static String CTNoRadioButton = getLocator("CTNoRadioButton");
	
	/**
	 * Select option.
	 *
	 * @param selection the selection
	 */
	public void selectOption(String selection) {
		
				 waitForPageToLoad();
				 String elementToSelect= "";
				 if (selection.equalsIgnoreCase("Yes")) {
					  elementToSelect = CTYesRadioButton;
				 }
				 else {
					 elementToSelect = CTNoRadioButton;
				 }
			        wait(Until.elementsToBePresent(elementToSelect));
					clickOnElement(elementToSelect);
			       
		  }
	
}
