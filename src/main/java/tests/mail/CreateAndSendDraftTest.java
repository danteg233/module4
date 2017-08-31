package tests;

import business_objects.EMail;
import business_objects.StaticEmailFactory;
import core.Browser;
import core.PropertyFileReader;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.MyLogger;
import tests.mail.page_objects.LogInPage;
import tests.mail.page_objects.MailPage;

import static org.testng.Assert.assertTrue;

public class CreateAndSendDraftTest {
    private String userName, pass, baseUrl;

    @BeforeClass
    @Parameters({"userName", "pass"})
    public void setUp(String userName, String pass){
        MyLogger.info("---CreateAndSendDraftTest---");
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

    @Test(description = "make a draft and send the draft", dependsOnMethods = "logInTest")
    @Parameters({"to", "subj", "context"})
    public void createAndSendDraftTest(String to, String subj, String context) throws Exception {
        EMail email = StaticEmailFactory.getEmailWithAllFields(to, subj, context);
        MyLogger.info("---CREATING A DRAFT---");
        assertTrue(new MailPage().makeDraft(email).goToDraftFolder().isEmailExist(email).sendEmail().isSentLocatorPresented());
        MyLogger.info("---DELETING DRAFT---");
        assertTrue(new MailPage().toSentFolder().deleteEmail(email).isSentFolderEmpty());
    }

    @Test(dependsOnMethods = "createAndSendDraftTest")
    public void logOutTest(){
        MyLogger.info("---LOGGING OUT---");
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
