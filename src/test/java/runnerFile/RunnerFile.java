package runnerFile;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * The Class RunnerFile.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"}, glue = {"stepDefinition"},
    plugin = { "pretty", "html:target/cucumber-reports", "junit:target/cucumber-reports/cucumber.xml","json:target/cucumber.json" }, monochrome = true)
public class RunnerFile extends AbstractTestNGCucumberTests {

}
