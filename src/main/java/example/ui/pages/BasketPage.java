package example.ui.pages;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class BasketPage extends PageObject{
	
	@FindBy(css=".prod-desc-col>p>small")
	private WebElementFacade fldProductCode;	
	
	public String getProductCode(){
		
    	return fldProductCode.getText();
	}
}
