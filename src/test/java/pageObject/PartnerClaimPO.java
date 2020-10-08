package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;
// TODO: Auto-generated Javadoc
/**
 * The Class HomePagePO.
 */
public class PartnerClaimPO  extends WebDriverBase{
	
	/** The PC yes radio button. */
	private static String PCYesRadioButton = getLocator("PCYesRadioButton");
	
	/** The PC no radio button. */
	private static String PCNoRadioButton = getLocator("PCNoRadioButton");
	
	/**
	 * Select option.
	 *
	 * @param selection the selection
	 */
	public void selectOption(String selection) {
		
				 waitForPageToLoad();
				 String elementToSelect= "";
				 if (selection.equalsIgnoreCase("Yes")) {
					  elementToSelect = PCYesRadioButton;
				 }
				 else {
					 elementToSelect = PCNoRadioButton;
				 }
			        wait(Until.elementsToBePresent(elementToSelect));
					clickOnElement(elementToSelect);
			       
		  }
	}
	
	
