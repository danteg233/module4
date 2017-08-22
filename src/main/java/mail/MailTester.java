package mail;

import core.PropertyFileReader;
import core.WebDriverSingleton;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class MailTester {
    /*
    add parameters property files add maven
     */
        private String userName, pass, to, subj, context, baseUrl;

        @BeforeClass
        @Parameters({"userName", "pass", "to", "subj", "context"})
        public void setUp(String userName, String pass, String to, String subj, String context) throws Exception {
            PropertyFileReader.read("baseUrl");
            this.userName = userName;
            this.pass = pass;
            this.to = to;
            this.subj = subj;
            this.context = context;
            baseUrl = PropertyFileReader.getBaseUrl();
        }

        @Test
        public void test() throws Exception {
            MailPage mailPage = new LogInPage().logIn(userName, pass, baseUrl);
            mailPage.makeDraft(to, subj,context).sendEmail().toSentFolder();
            mailPage.toDisk();
            YandexDiskPage yandexDiskPage = new YandexDiskPage();
            yandexDiskPage.dragAndDrop();
            yandexDiskPage.toMail().logOff();

        }

        @AfterClass
        public void tearDown() throws Exception {
            WebDriverSingleton.kill();
        }

}
