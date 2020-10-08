package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class JointUniversalCreditClaimPO  extends WebDriverBase{
	
/** The JUC yes radio button. */
private static String JUCYesRadioButton = getLocator("JUCYesRadioButton");
	
	/** The PC no radio button. */
	private static String JCNoRadioButton = getLocator("JUCNoRadioButton");
	
	/**
	 * Select option.
	 *
	 * @param selection the selection
	 */
	public void selectOption(String selection) {
		
				 waitForPageToLoad();
				 String elementToSelect= "";
				 if (selection.equalsIgnoreCase("Yes")) {
					  elementToSelect = JUCYesRadioButton;
				 }
				 else {
					 elementToSelect = JCNoRadioButton;
				 }
			        wait(Until.elementsToBePresent(elementToSelect));
					clickOnElement(elementToSelect);
			       
		  }
	
}
