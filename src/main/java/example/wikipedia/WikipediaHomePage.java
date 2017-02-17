package example.wikipedia;

/**
 * @author : ABDUL SAIF
 * @date   : 07/12/2016
 */

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;

//SPECIFY and INITIALISE URL address
@DefaultUrl("https://www.wikipedia.org/")

public class WikipediaHomePage extends PageObject {

    //ATTRIBUTES by ID
    public String SEARCH = "#searchInput";
    public String CHECK = "#firstHeading";

    //METHODS
    //1. INSERT text and then ENTER
    public void search(String actionName)
    {
        $(SEARCH).type(actionName).then().sendKeys(Keys.ENTER);
    }

    //2. CHECK the page has loaded correctly
    public void checkPage() {
        String CHECKED = $(CHECK).getText();
        System.out.println("Successfully ran Wikipedia: " + CHECKED);
    }

}
