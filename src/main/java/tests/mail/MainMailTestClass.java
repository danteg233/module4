package tests.mail;

import core.Browser;
import core.PropertyFileReader;
import model.mail.page_objects.LogInPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import reporting.MyLogger;

public class MainMailTestClass {
    @BeforeClass
    @Parameters({"userName", "pass"})
    public void setUp(String userName, String pass) throws Exception {
        MyLogger.info("---CreateAndSendDraftTestClass---");
        PropertyFileReader.read("baseUrl");
        String baseUrl = PropertyFileReader.getValue();
        Assert.assertTrue(new LogInPage().logIn(userName, pass, baseUrl).isLoginConfirm());
    }

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
