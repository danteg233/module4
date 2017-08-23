package tests.mail;

import core.PropertyFileReader;
import core.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.mail.page_objects.YandexDiskPage;

import static org.testng.Assert.assertTrue;

public class DragAndDropTest {

    private String userName, pass, baseUrl;

    @BeforeClass
    @Parameters({"userName", "pass"})
    public void setUp(String userName, String pass){
        PropertyFileReader.read("yandexDiskBaseUrl");
        this.userName = userName;
        this.pass = pass;
        baseUrl = PropertyFileReader.getBaseUrl();
    }


    @Test
    public void test(){
        YandexDiskPage yandexDiskPage = new YandexDiskPage();
        yandexDiskPage.logIn(userName, pass, baseUrl);
        assertTrue(yandexDiskPage.isLoginConfirm(), "Couldn't login to the page");
        yandexDiskPage.dragAndDrop();
        Assert.assertTrue(yandexDiskPage.isProgressbarPresented(), "No such element presented");
        yandexDiskPage.lofOff();
        assertTrue(yandexDiskPage.isLogOutConfirm(), "Couldn't log out from the site");
    }

    @AfterClass
    public void tearDown() throws Exception {
        WebDriverSingleton.kill();
    }
}
