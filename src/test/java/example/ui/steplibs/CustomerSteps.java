package example.ui.steplibs;

import example.ui.pages.BasketPage;
import example.ui.pages.HomePage;
import example.ui.pages.ProductDetailsPage;
import example.ui.pages.SearchResultsPage;
import net.thucydides.core.annotations.Step;

public class CustomerSteps {
	
	private HomePage homePage;
	private SearchResultsPage searchResultsPage;
	private ProductDetailsPage productDetailsPage;
	private BasketPage basketPage;
	private int itemsPerRow=3;
	
	@Step
	public void openHomepage() {
		
		homePage.open();		
	}
	
	@Step
	public String getHomePageTitle() {
				
		return homePage.getTitle();
	}
	
	@Step
	public void searchWithKeyword(String keyword) {
		
		homePage.search(keyword);		
	}
	
	@Step
	public String getSearchResultsPageTitle() {
				
		return searchResultsPage.getTitle();
	}	
	
	@Step
	public String getSearchPageBreadcrumb() {
				
		return searchResultsPage.getBreadcrumbText();
	}
	
	@Step
	public void selectSearchResult(int resultNum) {
		
		int row = (int)(resultNum / itemsPerRow);
		int col = resultNum % itemsPerRow;
		searchResultsPage.selectSearchResult(row+1, col);
	}
	
	@Step
	public void addItemToBasket(){
		
		productDetailsPage.clickAddToBasketButton();
	}
	
	@Step
	public String getAddToBasketResult(){
		
		return productDetailsPage.getAddToBasketResult();
	}
	
	@Step
	public void viewBasket(){
		
		productDetailsPage.viewBasket();
	}
	
	@Step
	public String checkBasketContainsSelectedProduct(){
		
		return basketPage.getProductCode();
	}
}