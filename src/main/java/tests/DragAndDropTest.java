package tests;

import core.Browser;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

@CucumberOptions(features = {"src/test/java/features"}, glue = "steps")
public class DragAndDropTest extends AbstractTestNGCucumberTests{

    @AfterMethod
    public void takeScreenshotOnTestFailure(ITestResult iTestResult){
        if(iTestResult.getStatus() == ITestResult.FAILURE){
            Browser.takeScreenshot();
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        Browser.kill();
    }
}
