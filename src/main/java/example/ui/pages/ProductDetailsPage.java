package example.ui.pages;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductDetailsPage extends PageObject{
	
	//@FindBy(xpath=".//*[@id='prod-add-to-basket']/div[2]/div/fieldset[2]/div/span/input[11]")
	@FindBy(css=".btn-plrg-addtobasket.button.fn_sc_scAdd")
	private WebElementFacade btnAddToBasket;
	
	@FindBy(id="addToBasketConfirm")
	private WebElementFacade fldAddToBasketResult;
	
	@FindBy(css=".view-basket-link")
	private WebElementFacade btnViewBasket;
	
	public void clickAddToBasketButton(){
		
		btnAddToBasket.click();
	}
	
	public String getAddToBasketResult(){
		
    	return fldAddToBasketResult.getText();
	}
	
	public void viewBasket(){
		
		btnViewBasket.click();
	}	
}
