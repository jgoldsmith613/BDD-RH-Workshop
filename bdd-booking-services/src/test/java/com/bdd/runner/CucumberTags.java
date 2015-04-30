package com.bdd.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", monochrome = true, glue = "com/bdd/steps/", strict = true, tags = { "@wip" })
public class CucumberTags {

}
