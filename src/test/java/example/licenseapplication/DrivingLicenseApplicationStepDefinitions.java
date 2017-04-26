package example.licenseapplication;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DrivingLicenseApplicationStepDefinitions {

    DrivingLicenseApplicationPage drivingLicenseApplicationPage;

    @Given("^I open the Home Office Driving License Application Page$")
    public void I_open_license_application(){
        drivingLicenseApplicationPage.open();
        drivingLicenseApplicationPage.startApplication();
        drivingLicenseApplicationPage.startRegistration();
    }

    @When("^I select the title '(.*)'$")
    public void select_Title(String actionName){
        drivingLicenseApplicationPage.selectTitle(actionName);
    }

    @When("^I input the surname '(.*)' and then I input the forename '(.*)'$")
    public void input_name(String surName, String forName){
        drivingLicenseApplicationPage.insertName(forName, surName);
    }

    @When("^I select the gender '(.*)'$")
    public void input_Gender(String actionName){
        drivingLicenseApplicationPage.selectGender(actionName);
    }

    @When ("^I input my date of birth '(.*)'/'(.*)'/'(.*)'$")
    public void select_date_of_birth(String day, String month, String year){
        drivingLicenseApplicationPage.inputDateOfBirth(day, month, year);
    }

    @When("^I select the country '(.*)'$")
    public void select_Country(String actionName){
        drivingLicenseApplicationPage.selectCountryOfBirth(actionName);
    }

    @When("^I submit my details$")
    public void submit_details(){
        drivingLicenseApplicationPage.submitDetails();
    }

    @Then("^I get a hazard message$")
    public void check_hazard(){
        drivingLicenseApplicationPage.check_hazard();
    }

    @Then("^My application ends because I am too young$")
    public void check_age_invalid(){
        drivingLicenseApplicationPage.check_age_invalid();
    }

    @When("^I enter my House Number '(.*)' and Postcode '(.*)'$")
    public void enter_address(String houseNumber, String postCode){
        drivingLicenseApplicationPage.inputAddress(houseNumber, postCode);
        drivingLicenseApplicationPage.submitDetails();
        drivingLicenseApplicationPage.submitDetails();

    }

    @When("^I enter how long I have lived there '(.*)' years and '(.*)' months$")
    public void enter_time_lived(String years, String months){
        drivingLicenseApplicationPage.timeAtAddress(years, months);
        drivingLicenseApplicationPage.submitDetails();
    }

    @Then("^I then reach the '(.*)' page$")
    public void check_destination(String actionName){
        drivingLicenseApplicationPage.checkHeading(actionName);
    }

}
