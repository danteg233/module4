package tests.mail;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeListener;
import core.PropertyFileReader;
import core.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.fail;

public class MailTest {
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
            Assert.assertTrue(yandexDiskPage.isProgressbarPresented(), "No such element presented");
            yandexDiskPage.toMail().logOff();

        }

        @AfterClass
        public void tearDown() throws Exception {
            WebDriverSingleton.kill();
        }

}

/*
TODO: create email BO (read data from XML). Change asserts.
 */

/*
java -Dwebdriver.chrome.driver=C:\Windows\Webdrivers\chromedriver.exe -jar C:\Windows\Webdrivers\selenium\selenium-server-standalone-3.5.0.jar -role node -hub http://localhost:4444/grid/register  -browser "browserName=chrome, -maxSession=5, platform=WINDOWS" -port 5696

pause

java -jar selenium-server-standalone-3.5.0.jar -role hub -port 4444

 */