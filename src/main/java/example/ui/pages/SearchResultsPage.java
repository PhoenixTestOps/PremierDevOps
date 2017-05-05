package example.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SearchResultsPage extends PageObject{
		
	@FindBy(id="breadcrumbs")
	private WebElementFacade fldBreadcrumb;
	
	public String getBreadcrumbText(){
				
		return fldBreadcrumb.getText();
	}
	
	public void selectSearchResult(int row, int col){
		
    	WebElement searchResult = getDriver().findElement(By.xpath(".//*[@id='product-grid']//div[@class='products']/div["+row+"]/article["+col+"]"));
    	searchResult.findElement(By.tagName("a")).click();    	
	}
}
