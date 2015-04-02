package com.bdd.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", monochrome = true, glue = "com/bdd/steps/", strict = true)
public class CucumberTest {

}
