package com.orevan.serenity.steps.tdd;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by tolaf on 19/04/2017.
 */

@RunWith(SerenityRunner.class)
public class basicAnnotations {

    //import org.junit.*;


/*        private LumaBasePage lumaBasePage;

        public basicAnnotations(LumaBasePage lumaBasePage){
            this.lumaBasePage = lumaBasePage;
        }*/

        // Run once, e.g. Database connection, connection pool
        @BeforeClass
        public static void runOnceBeforeClass() {
            System.out.println("@BeforeClass - runOnceBeforeClass");
        }

        // Run once, e.g close connection, cleanup
        @AfterClass
        public static void runOnceAfterClass() {
            System.out.println("@AfterClass - runOnceAfterClass");
        }

        // Should rename to @BeforeTestMethod
        // e.g. Creating an similar object and share for all @Test
        @Before
        public void runBeforeTestMethod() {
            System.out.println("@Before - runBeforeTestMethod");
        }

        // Should rename to @AfterTestMethod
        @After
        public void runAfterTestMethod() {
            System.out.println("@After - runAfterTestMethod");
        }

        @Test
        public void test_method_1() {
            System.out.println("@Test - test_method_1");
            //lumaBasePage.goToLuma();
        }

        @Test
        public void test_method_2() {
            System.out.println("@Test - test_method_2");
        }

    }
