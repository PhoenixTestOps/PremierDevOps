package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Created by
 */
public class SearchPage extends PageObject {

    private String searchPagePoint = "http://localhost:3000/#/";
    private String searchPageTitle = "Search Page";


    @FindBy(css ="#searchbodyid") private WebElementFacade searchPageBody;
    private By errorMessageBlock = (By) By.cssSelector("errormessage");


    //methods
    public void searchPageSimulation(){
        openAt(searchPagePoint);
        waitABit(5);
    }
    public String returnSearchPageTitle(){
        return searchPageTitle;
    }
    public void verifySearchPageContent(){
        shouldBeVisible(searchPageBody);
    }
    public void searchPageError(String error){
        verifySearchPageContent();
        shouldBeVisible(errorMessageBlock);
        waitForTextToAppear(element(errorMessageBlock), error);
    }

}
