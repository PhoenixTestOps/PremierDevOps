package example.homeoffice;

/**
 * @author : ABDUL SAIF
 * @date   : 01/12/2016
 */

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;

//SPECIFY and INITIALISE URL address
@DefaultUrl("https://www.gov.uk/government/organisations/home-office")

public class HomeofficeHomePage extends PageObject {

    //ATTRIBUTES by ID
    String USERNAME = "#cred1";

    String PASSWORD = "#cred2";

    //ATTRIBUTES by XPATH
    @FindBy(xpath = "//*[@id='site-search-text']")
    WebElementFacade SEARCH;

    @FindBy(xpath = "//*[@id='js-live-search-results']/li[1]/h3/a")
    WebElementFacade SELECTLINK;

    @FindBy(xpath = "//*[@id='get-started']/a\n")
    WebElementFacade STARTNOW;


    //METHODS
    //1. SEARCH for input
    public void search(String actionName) {
        $(SEARCH).type(actionName).then().sendKeys(Keys.ENTER);
    }

    //2. SELECT and ENTER first item on page
    public void selectLink(String actionName) {
        $(SELECTLINK).click();
    }

    //3. SELECT button
    public void startNow(String actionName) {
        $(STARTNOW).click();
    }

    //4. INPUT username
    public void insertUsername(String actionName) {
        $(USERNAME).type(actionName);
    }

    //5. INPUT password
    public void insertPassword(String actionName) {
        $(PASSWORD).type(actionName);
    }


}
