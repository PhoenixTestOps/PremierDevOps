bdd-acceptance-test
===================

> ***This page is in the process of being updated***

This framework provides the capability to create and execute business facing tests using the BDD software development process. It provides a wrapper around [Serenity](http://www.thucydides.info), a BDD open source library. The key features provided are:

1. Tests can be written in feature files using the "Given, When, Then" structure (by using Gherkin, which is the name of Cucumber's DSL).
2. JIRA ID's can be mapped to feature files to allow links to be created within the report generated by Serenity.
3. Feature coverage can be easily viewed if your JIRA project is categorised into epics and links.
4. The status of a JIRA story can be updated, depending on the outcome of the tests.
5. Webdriver support using [Serenity Page Objects](http://serenity-bdd.info/docs/serenity/#_writing_serenity_page_objects)
6. Testing REST services using [Rest Assured](http://serenity-bdd.info/docs/serenity/#_testing_rest_with_serenity_bdd)


Configuration
-------------
Once you have cloned the [qa-framework/bdd-acceptance-test](https://gitlab.com/qa-framework/bdd-acceptance-test) repo locally, the following updates will be required in the project:


### cucumber-jvm-parallel-plugin
The purpose of this plugin is to facilitate parallel execution of tests as it generates a Cucumber runner for each scenario/feature file found in your project. It must be installed to your local .m2 folder using the following command (assuming you are in the root dir):

`mvn install:install-file -Dfile=src/main/java/dependencies/cucumber-jvm-parallel-plugin/cucumber-jvm-parallel-plugin-2.1.1-SNAPSHOT.jar -DpomFile=src/main/java/dependencies/cucumber-jvm-parallel-plugin/pom.xml`

Runners are created on the fly and can be found under `target/generated-test-sources/cucumber`. They take the naming convention `FeatureXXIT.java`, where XX is a one up counter.

### serenity.properties
`serenity.properties` lives in the root directory of your project. It is required in order to configure a number of property values that Serenity requires in order to execute tests and integrate with JIRA. Please review the file for a list 

Comments have been provided in the file to help you understand the purpose of the property. The entire list of properties can be found [here](http://serenity-bdd.info/docs/serenity/#_serenity_system_properties_and_configuration)


### < env >.properties
If you have environment specific configuration that your tests need access to at runtime, then an environment file can be created. The location of the file is currently set to `src/main/resources`

There is no strict naming convention, but the name of the file (excluding extension) must be passed in as a cmd line parameter when running your tests from Maven. For example, if you call your file `local.properties`, then the cmd line arg to pass to the `mvn` command would be `-Denv=local`.


### pom.xml
The pom.xml requires minimal configuration in order to execute the tests. There are 2 specific capabilities that will need to be enabled if you wish to use them:

1. Enable JIRA requirements in the Serenity report
The `serenity-jira-requirements-provider` dependency will allow JIRA to determine the epics and stories that you have defined and list them in the Requirements page of the report. This dependency is required by the `serenity-maven-plugin` plugin and is disabled by default. If you have connectivity to a working instance of JIRA and wish to provide epic/story coverage in the report, then this dependency should be enabled.


2. Enable post execution updates within Zephyr
This plugin offers the capability to create Zephyr tests for your scenarios and update their execution status. It is disabled by default and can be enabled if you have connectivity to a working instance of JIRA and Zephyr. Please visit the [bdd-jira-zephyr-plugin](https://gitlab.com/qa-framework/bdd-jira-zephyr-plugin) homepage for more information about the plugin.


Usage
-----

The framework is divided as follows:

| Folder             | Description           |
| ------------------ | --------------------- |
  src/main/java      | Application sources
  src/main/resources | Application resources
  src/test/java      | Step definitions
  src/test/resources | Test resources and Feature files

Examples have been provided within the framework to help build your understanding.

The tests can be executed in a number of ways in Maven:

| Command														| Description           |
| ------------------------------------------------------------- | --------------------- |
  `mvn clean verify`											| Run all scenarios
  `mvn clean verify -Denv=local`							| Run all scenarios and pass in an environment file
  `mvn clean verify -Dit.test=Feature01IT`				| Run a single scenario
  `mvn failsafe:integration-test -Dit.test=Belly01IT`	| Run a single scenario, but without recompiling the code


Reports
-------

Once you have executed the tests, Serenity will create the report under the `target/site/serenity/index.html`.