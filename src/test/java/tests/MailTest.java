package tests;

import core.Browser;
import core.PropertyFileReader;
import org.testng.ITestResult;
import org.testng.annotations.*;
import tests.mail.business_objects.EMail;
import tests.mail.business_objects.StaticEmailFactory;
import tests.mail.page_objects.DraftPage;
import tests.mail.page_objects.LogInPage;
import tests.mail.page_objects.MailPage;
import tests.mail.page_objects.SendEmailPage;
import reporting.MyLogger;

import static org.testng.Assert.assertTrue;

public class MailTest{
        private String userName, pass, baseUrl;
        private EMail email;

        @BeforeClass
        @Parameters({"to", "subj", "context", "userName", "pass"})
        public void setUp(String to, String subj, String context, String userName, String pass){
            MyLogger.info("---MAIL TEST---");
            email = StaticEmailFactory.getEmailWithAllFields(to, subj, context);
            PropertyFileReader.read("baseUrl");
            this.userName = userName;
            this.pass = pass;
            baseUrl = PropertyFileReader.getValue();
        }

        @Test(description = "log in")
        public void logInTest() throws Exception {
            LogInPage logInPage = new LogInPage().logIn(userName, pass, baseUrl);
            assertTrue(logInPage.isLoginConfirm(), "Couldn't login to the mail");
            MyLogger.info("---LOGIN SUCCESSFULLY---");
        }

        @Test(description = "send a email", dependsOnMethods = "logInTest")
        public void sendEmailTest() throws Exception {
            MailPage mailPage = new DraftPage().makeDraft(email);
            MyLogger.info("---CREATING A DRAFT ....---");
            assertTrue(new SendEmailPage().sendEmail(email).isSentLocatorPresented());
            mailPage.toSentFolder(email);
            assertTrue(mailPage.isSentFolderEmpty());
            mailPage.logOff();
            assertTrue(mailPage.isLogOutCheckPresented(), "Couldn't log out!");
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
