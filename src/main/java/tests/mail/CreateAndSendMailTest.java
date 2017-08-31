package tests.mail;

import business_objects.EMail;
import business_objects.StaticEmailFactory;
import core.Browser;
import core.PropertyFileReader;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.MyLogger;
import model.mail.page_objects.LogInPage;
import model.mail.page_objects.MailPage;

import static org.testng.Assert.assertTrue;

public class CreateAndSendMailTest {
    private String userName, pass, baseUrl;

    @BeforeClass
    @Parameters({"userName", "pass"})
    public void setUp(String userName, String pass){
        MyLogger.info("---CreateAndSendMailTest---");
        PropertyFileReader.read("baseUrl");
        this.userName = userName;
        this.pass = pass;
        baseUrl = PropertyFileReader.getValue();
    }

    @Test(description = "log in")
    public void logInTest() throws Exception {
        assertTrue(new LogInPage().logIn(userName, pass, baseUrl).isLoginConfirm(), "Couldn't login to the mail");
        MyLogger.info("---LOGIN SUCCESSFULLY---");
    }


    @Test(dependsOnMethods = "logInTest")
    @Parameters({"to", "sameSubjAndText"})
    public void createAndSendMailTest(String to, String sameSubjAndText) throws Exception {
        EMail email = StaticEmailFactory.getEmailWithSameSubjAndText(to, sameSubjAndText);
        assertTrue(new MailPage().makeDraft(email).sendEmail().isSentLocatorPresented());
    }

    @Test(dependsOnMethods = "createAndSendMailTest")
    public void logOutTest(){
        assertTrue(new MailPage().logOff().isLogOutCheckPresented(), "Couldn't log out!");
        MyLogger.info("---LOGGED OUT---");
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
