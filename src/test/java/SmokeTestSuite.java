package com.orevan.serenity;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by tolaf on 02/03/2017.
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/", tags = {"@regression"})
public class SmokeTestSuite {
}
