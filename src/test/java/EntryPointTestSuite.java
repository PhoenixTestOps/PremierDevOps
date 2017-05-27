package com.orevan.serenity;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by tolaf on 08/04/2017.
 */


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/luma/", tags = {"@"})
public class EntryPointTestSuite {
}