package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
	(
		features = "./Features/Customers.feature",
		glue = "stepDefinitions",
		dryRun=false,
		monochrome=true,
		plugin= {"pretty","html: report/testoutput.html"},
		tags= "@sanity or @regression"
		)
public class TestRunner {

}

