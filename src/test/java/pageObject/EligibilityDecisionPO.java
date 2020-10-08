package pageObject;

import static com.seeta.common.framework.cucumber.web.objectlocators.WebObjectLocators.getLocator;
import static org.junit.Assert.assertTrue;

import com.seeta.common.framework.cucumber.web.core.Until;
import com.seeta.common.framework.cucumber.web.core.WebDriverBase;

public class EligibilityDecisionPO  extends WebDriverBase{
	
/** The CT yes radio button. */
private static String EDResultText = getLocator("EDResultText");

	
	/**
	 * Select option.
	 *
	 * @param selection the selection
	 */
	public void getMessage(String message) {
		
				 waitForPageToLoad();
				 wait(Until.elementsToBePresent(EDResultText));     
				 System.out.println("Your Eligibility Result is "+findElement(EDResultText).getText());
				 assertTrue( (findElement(EDResultText).getText()).contains("You get help with NHS costs"));			       
		  }
	
}
