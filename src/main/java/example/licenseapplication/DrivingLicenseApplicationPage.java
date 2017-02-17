package example.licenseapplication;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

/**
 * @author : JULIA TUCKER
 * @date : 20/12/2016
 */

//SPECIFY AND INITIALISE URL ADDRESS
@DefaultUrl("https://www.gov.uk/apply-first-provisional-driving-licence")
public class DrivingLicenseApplicationPage extends PageObject {

    //ATTRIBUTES BY XPATH
    @FindBy(xpath="//*[@id=\"get-started\"]/a")
    WebElementFacade STARTNOW;

    @FindBy(xpath="//*[@id=\"fapNetui_Form_0\"]/fieldset/ul[4]/li/a")
    WebElementFacade REGISTER;

    @FindBy(xpath="//*[@id=\"formSubmit\"]/div[3]/input[1]")
    WebElementFacade REGISTERNEXT;

    @FindBy(xpath="//*[@id=\"fapwlw-select_key:{actionForm.title}\"]")
    WebElementFacade TITLESELECT;

    @FindBy(xpath="//*[@id=\"fap{actionForm.forname}\"]")
    WebElementFacade FORENAME;

    @FindBy(xpath="//*[@id=\"fap{actionForm.surname}\"]")
    WebElementFacade SURNAME;

    @FindBy(xpath="//*[@id=\"fapwlw-select_key:{actionForm.gender}\"]")
    WebElementFacade GENDERSELECT;

    @FindBy(xpath="//*[@id=\"fapwlw-select_key:{actionForm.dateOfBirthDay}\"]")
    WebElementFacade DAYSELECT;

    @FindBy(xpath="//*[@id=\"fapwlw-select_key:{actionForm.dateOfBirthMonth}\"]")
    WebElementFacade MONTHSELECT;

    @FindBy(xpath="//*[@id=\"fap{actionForm.dateOfBirthYear}\"]")
    WebElementFacade YEAR;

    @FindBy(xpath="//*[@id=\"fapwlw-select_key:{actionForm.countryOfBirth}\"]")
    WebElementFacade COUNTRYSELECT;

    @FindBy(xpath="//*[@id=\"formSubmit\"]/div[3]/input[1]")
    WebElementFacade SUBMIT;

    @FindBy(xpath="//*[@id=\"bodyContent\"]/h3")
    WebElementFacade MAINHEADING;

    @FindBy(xpath="//*[@id=\"fapNetui_Form_0\"]/h4")
    WebElementFacade AGEINVALID;

    @FindBy(xpath="//*[@id=\"fap{actionForm.houseNameOrNumber}\"]")
    WebElementFacade HOUSENUMBER;

    @FindBy(xpath = "//*[@id=\"fap{actionForm.postcode}\"]")
    WebElementFacade POSTCODE;

    @FindBy(xpath = "//*[@id=\"fap{actionForm.years}\"]")
    WebElementFacade YEARSLIVED;

    @FindBy(xpath = "//*[@id=\"fap{actionForm.months}\"]")
    WebElementFacade MONTHSLIVED;

    @FindBy(xpath = "//*[@id=\"bodyContent\"]/h3")
    WebElementFacade PAGEHEADING;

    //ATTRIBUTES BY CLASS NAME
    @FindBy(className = "hazard")
    WebElementFacade HAZARD;


    //METHODS
    //1. START application
    public void startApplication(){
        $(STARTNOW).click();
    }

    //2. SELECT register
    public void startRegistration(){
        $(REGISTER).click();
        $(REGISTERNEXT).click();
    }

    //3. SELECT title
    public void selectTitle(String actionName) {
        element(TITLESELECT).selectByVisibleText(actionName);
    }

    //4. INPUT forename
    public void insertName(String foreName, String surName){
        $(FORENAME).type(foreName);
        $(SURNAME).type(surName);
    }

    //6. SELECT gender
    public void selectGender(String actionName){
        element(GENDERSELECT).selectByVisibleText(actionName);
    }

    //7. INPUT date of birth
    public void inputDateOfBirth(String day, String month, String year){
        element(DAYSELECT).selectByVisibleText(day);
        element(MONTHSELECT).selectByVisibleText(month);
        $(YEAR).type(year);
    }

    //8. SELECT country of birth
    public void selectCountryOfBirth(String actionName){
        element(COUNTRYSELECT).selectByVisibleText(actionName);
    }

    //9. SUBMIT details
    public void submitDetails(){
        $(SUBMIT).click();
    }

    //11. CHECK hazard
    public  void check_hazard(){
        assertEquals(HAZARD.getText(), "Please complete all fields marked *");
    }

    //12. CHECK age invalid
    public void check_age_invalid(){
        assertEquals(AGEINVALID.getText(), "Age invalid");
    }

    //13. INSERT address
    public void inputAddress(String houseNumber, String postCode){
        HOUSENUMBER.type(houseNumber);
        POSTCODE.type(postCode);
    }

    //15. INSERT time at address
    public void timeAtAddress(String years, String months){
        YEARSLIVED.type(years);
        MONTHSLIVED.type(months);
    }

    //16. CHECK page heading
    public void checkHeading(String actionName){
        assertEquals(PAGEHEADING.getText(), actionName);
    }
}