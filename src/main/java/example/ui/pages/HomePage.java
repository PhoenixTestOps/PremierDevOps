package example.ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.johnlewis.com")
public class HomePage extends PageObject{
	
	@FindBy(id="search-keywords")
	private WebElementFacade fldSearch;
	
	public void search(String keyword){
		
		fldSearch.type(keyword).then().sendKeys(Keys.ENTER);
	}
}
