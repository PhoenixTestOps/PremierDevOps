package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Created by
 */
public class WebAppHomePage extends PageObject {

    private String webAppEntryPoint = "http://localhost:3000/#/";
    private String homePageTitle = "Home Page";

    @FindBy(css ="#webappbodyid") private WebElementFacade webAppPageBody;
    @FindBy(css ="#username") private WebElementFacade userName;
    @FindBy(css ="#password") private WebElementFacade passWord;
    @FindBy(css ="#login") private WebElementFacade loginButton;

    //methods
    public void goToEntryPoint(){
        openAt(webAppEntryPoint);
    }
    public String returnHomePageTitle(){
        return homePageTitle;
    }
    public void verifyWebAppHomePage(){
        shouldBeVisible(webAppPageBody);
    }
    public void enterUsername(String username){
        userName.clear();
        userName.sendKeys(username);
    }
    public void enterPassword(String password){
        passWord.clear();
        passWord.sendKeys(password);
    }
    public void clickLoginButton(){
        loginButton.click();
    }
}
