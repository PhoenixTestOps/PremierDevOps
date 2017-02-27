package example.theorytest;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author : JULIA TUCKER
 * @date : 20/12/2016
 */

//SPECIFY AND INITIALISE URL ADDRESS
@DefaultUrl("https://www.gov.uk/book-theory-test")
public class TheoryTestBookingPage extends PageObject{
	
	//ID of progress dialog overlay
    private String progressDialogID = "progressDialog";

    //ATTRIBUTES BY XPATH
    @FindBy(xpath="//*[@id=\"get-started\"]/a")
    WebElementFacade STARTNOW;

    @FindBy(xpath = "//*[@id=\"inputLAST_NAME\"]")
    WebElementFacade SURNAME;

    @FindBy(xpath="//*[@id=\"inputFIRST_NAME\"]")
    WebElementFacade FORENAME;

    @FindBy(xpath = "//*[@id=\"inputCLIENT_CANDIDATE_ID\"]")
    WebElementFacade LICENSENUMBER;

    @FindBy(xpath="//*[@id=\"inputQUESTION1967.day\"]")
    WebElementFacade DAYSELECT;

    @FindBy(xpath="//*[@id=\"inputQUESTION1967.month\"]")
    WebElementFacade MONTHSELECT;

    @FindBy(xpath="//*[@id=\"inputQUESTION1967.year\"]")
    WebElementFacade YEARSELECT;

    @FindBy(xpath = "//*[@id=\"submitButton\"]")
    WebElementFacade SUBMIT;

    @FindBy(xpath = "//*[@id=\"nextButton\"]")
    WebElementFacade NEXT;

    @FindBy(xpath = "//*[@id=\"homePage\"]/div[3]/div/h1")
    WebElementFacade MAINHEADING;

    @FindBy(xpath = "//*[@id=\"signInPageHeader\"]")
    WebElementFacade SUBHEADING;

    @FindBy(xpath = "//*[@id=\"preApproved\"]/table/tbody")
    WebElementFacade AVAILABLETESTS;

    @FindBy(xpath = "//*[@id=\"LanguageField\"]/tbody")
    WebElementFacade AVAILABLELANGUAGES;

    @FindBy(xpath = "//*[@id=\"accomOptions\"]/tbody")
    WebElementFacade SUPPORTOPTIONS;

    @FindBy(xpath = "//*[@id=\"testCentersNearAddress\"]")
    WebElementFacade POSTCODE;

    @FindBy(xpath = "//*[@id=\"calendarForm\"]/div[1]/h1")
    WebElementFacade APPOINTMENTHEADING;

    @FindBy(xpath = "//*[@id=\"addressSearch\"]")
    WebElementFacade FINDADDRESS;

    @FindBy(xpath = "//*[@id=\"continueTop\"]")
    WebElementFacade CONTINUE;

    //ATTRIBUTES BY CLASSNAME
    @FindBy(className = "warningAreaContainer")
    WebElementFacade ERRORMESSAGE;

    //METHODS
    //1. START application
    public void startApplication(){
        $(STARTNOW).click();
    }

    //2. INPUT surname
    public void insertSurname(String actionName){
        $(SURNAME).type(actionName);
    }

    //3. INPUT forename
    public void insertForename(String actionName){
        $(FORENAME).type(actionName);
    }

    //4. INPUT license number
    public void insertLicenseNumber(String actionName){
        $(LICENSENUMBER).type(actionName);
    }

    //5. INPUT date of birth
    public void inputDayOfBirth(String actionName){
        element(DAYSELECT).selectByVisibleText(actionName);
    }
    public void inputMonthOfBirth(String actionName) {
        element(MONTHSELECT).selectByVisibleText(actionName);
    }
    public void inputYearOfBirth(String actionName) {
        element(YEARSELECT).selectByVisibleText(actionName);
    }

    //6. SUBMIT details
    public void submitDetails(){
        $(SUBMIT).click();
    }

    // 7. NEXT
    public void next(){
        $(NEXT).click();
    }

    //7. CHECK destination
    public void check_main_destination(String actionName){
            assertTrue(MAINHEADING.getText().equals(actionName));
    }
    public void check_sub_destination(String actionName) {
            assertTrue(SUBHEADING.getText().equals(actionName));

    }
    public void check_appointment_destination(String actionName){
        assertTrue(APPOINTMENTHEADING.getText().equals(actionName));
    }

    //8. CHECK error message
    public void check_error_message(String actionName){
            assertEquals(ERRORMESSAGE.getText(), "Please provide a valid response for the following fields: " + actionName);
    }

    //9. SELECT test
    public void test_selection(String actionName){

        AVAILABLETESTS.findBy(actionName).click();
    }

    //10. SELECT language
    public void language_selection(String actionName){
        AVAILABLELANGUAGES.findBy(By.id("LanguageField:"+actionName)).click();
    }

    //11. SELECT support option
    public void support_selection(String actionName){
        SUPPORTOPTIONS.findBy(By.id("accomOptions:"+actionName)).click();
    }

    //12. SUBMIT postcode
    public void submit_postcode(String actionName) {
        POSTCODE.type(actionName);
    }

    //13. FIND address
    public void find_address(){
        FINDADDRESS.click();
    }

    //14. CONTINUE
    public void continue_on(){
        CONTINUE.click();
    }

    /**
     * Wait for the progress dialog overlay to disappear.
     * This is to mitigate race conditions between page load time and driver execution speed. The issue is noticeable in Chrome by virtue of the error:
     * "WebDriverException: Element is not clickable..."
     */
    public void wait_for_progress_dialog(){
    	WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(progressDialogID)));
    }
}
