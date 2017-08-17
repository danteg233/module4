package mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class MailTester {
    /*
    add parameters property files add maven
     */
        private WebDriver driver;
        private String baseUrl;
        private StringBuffer verificationErrors = new StringBuffer();
        private String userName, pass, to, subj, context;

        @BeforeClass
        @Parameters({"userName", "pass", "to", "subj", "context"})
        public void setUp(String userName, String pass, String to, String subj, String context) throws Exception {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            mail.PropertyFileReader.read("baseUrl");
            driver = new FirefoxDriver();
            this.userName = userName;
            this.pass = pass;
            this.to = to;
            this.subj = subj;
            this.context = context;
            baseUrl = mail.PropertyFileReader.getBaseUrl();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test
        public void test() throws Exception {
            MailPage mailPage = new LogInPage(driver).logIn(userName, pass, baseUrl);
            mailPage.makeDraft(to, subj,context).sendEmail().toSendFolder();
        }

        @AfterClass
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

}
