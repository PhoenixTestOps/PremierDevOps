package example.theorytest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import example.utilities.DataMapping;

import java.io.IOException;

/**
 * @author : JULIA TUCKER
 * @date : 20/12/2016
 */
public class TheoryTestBookingStepDefinitions {

    TheoryTestBookingPage theoryTestBookingPage;

    @Given ("^I open the Home Office Book Theory Test Page$")
    public void I_open_theory_test_booking(){
        theoryTestBookingPage.open();
        theoryTestBookingPage.startApplication();
    }

    @When ("^I input the surname '(.*)' and I input the forename '(.*)'$")
    public void input_name(String surName, String forName){
        theoryTestBookingPage.insertSurname(surName);
        theoryTestBookingPage.insertForename(forName);
    }

    @When("^I input '(.*)' as my license number$")
    public void input_license(String actionName){
        theoryTestBookingPage.insertLicenseNumber(actionName);
    }

    @When ("^I input my birthdate '(.*)'/'(.*)'/'(.*)'$")
    public void select_date_of_birth(String day, String month, String year){
        theoryTestBookingPage.inputDayOfBirth(day);
        theoryTestBookingPage.inputMonthOfBirth(month);
        theoryTestBookingPage.inputYearOfBirth(year);
        theoryTestBookingPage.submitDetails();
    }


    @Then("^I am taken to the main '(.*)' page$")
    public void check_main_page_destination(String actionName){
        theoryTestBookingPage.check_main_destination(actionName);
    }

    @Then("^I am taken to the sub '(.*)' page$")
    public void check_sub_page_destination(String actionName){
        theoryTestBookingPage.check_sub_destination(actionName);
    }

    @Then("^The error message is '(.*)'$")
    public void check_error_message(String actionName){
        theoryTestBookingPage.check_error_message(actionName);
    }

    @When ("^I select the test '(.*)'$")
    public void select_test(String actionName) throws IOException {

        DataMapping testOptions  = new DataMapping();

        String filePath = "src/test/resources/test-data/mappings/TheoryTestOptions.csv";

        testOptions.fill_from_file(filePath);

        String testID = testOptions.get(actionName).toString();

        theoryTestBookingPage.test_selection(testID);
    }

    @When ("^I select the language '(.*)'$")
    public void select_language(String actionName) throws IOException {
        DataMapping languageOptions = new DataMapping();

        String filePath = "src/test/resources/test-data/mappings/TheoryLanguageOptions.csv";

        languageOptions.fill_from_file(filePath);

        String languageID = languageOptions.get(actionName).toString();

        theoryTestBookingPage.language_selection(languageID);
        theoryTestBookingPage.next();
    }

    @When ("^I respond '(.*)' to needing extra support$")
    public void select_support(String actionName) throws IOException {

        DataMapping supportOptions = new DataMapping();
        
        String filePath = "src/test/resources/test-data/mappings/TheorySupportOptions.csv";
        
        supportOptions.fill_from_file(filePath);

        String supportID = supportOptions.get(actionName).toString();

        theoryTestBookingPage.support_selection(supportID);
        theoryTestBookingPage.next();
    }

    @When ("^I enter the postcode '(.*)' and select the first center$")
    public void submit_postcode(String actionName){
        theoryTestBookingPage.submit_postcode(actionName);
        theoryTestBookingPage.find_address();
        theoryTestBookingPage.continue_on();
    }

    @Then ("^I reach the '(.*)' page$")
    public void check_appointment_page(String actionName){
        theoryTestBookingPage.check_appointment_destination(actionName);
    }



}
