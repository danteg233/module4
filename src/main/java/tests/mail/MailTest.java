package tests.mail;

import core.PropertyFileReader;
import core.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.mail.business_objects.EMail;
import tests.mail.page_objects.LogInPage;
import tests.mail.page_objects.MailPage;
import tests.mail.page_objects.YandexDiskPage;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class MailTest {
        private String userName, pass, baseUrl;
        private EMail email;

        @BeforeClass
        @Parameters({"to", "subj", "context", "userName", "pass", })
        public void setUp(String to, String subj, String context, String userName, String pass){
            email = new EMail(to, subj, context);
            PropertyFileReader.read("baseUrl");
            this.userName = userName;
            this.pass = pass;
            baseUrl = PropertyFileReader.getBaseUrl();
        }

        @Test(description = "log in")
        public void logInTest() throws Exception {
            LogInPage logInPage = new LogInPage().logIn(userName, pass, baseUrl);
            assertTrue(logInPage.isLoginConfirm(), "Couldn't login to the mail");
        }

        @Test(description = "send a email", dependsOnMethods = "logInTest")
        public void sendEmailTest() throws Exception {
            MailPage mailPage = new MailPage();
            mailPage.makeDraft(email);
            mailPage.sendEmail(email);
            assertTrue(mailPage.isSentLocatorPresented());
            mailPage.toSentFolder(email);
            assertTrue(mailPage.isSentFolderEmpty());
            mailPage.logOff();
            assertTrue(mailPage.isLogOutCheckPresented(), "Couldn't log out!");
        }

        @AfterClass
        public void tearDown() throws Exception {
            WebDriverSingleton.kill();
        }
}
