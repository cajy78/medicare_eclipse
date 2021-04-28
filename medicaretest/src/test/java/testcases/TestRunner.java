package testcases;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		monochrome = true,
		plugin = {"pretty", "html:target/cucumber-html", "json:target/cucumber.json"},
		features = "src/test/java/features",
		tags = "@AdminUser",
		glue = {"stepdef"}
		)

@Test
public class TestRunner extends AbstractTestNGCucumberTests
{
}