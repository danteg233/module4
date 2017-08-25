package tests.yandex;

import core.PropertyFileReader;
import core.WebDriverSingleton;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.mail.page_objects.DraftPage;
import tests.yandex.business_objects.DragDropPage;
import tests.yandex.business_objects.RestoreElementPage;
import tests.yandex.business_objects.YandexDiskPage;

import static org.testng.Assert.assertTrue;

@CucumberOptions(features = {"src/test/java/features"}, glue = "steps")
public class DragAndDropTest extends AbstractTestNGCucumberTests{

//    private String userName, pass, baseUrl;
//
//    @BeforeClass
//    @Parameters({"userName", "pass"})
//    public void setUp(String userName, String pass){
//        PropertyFileReader.read("yandexDiskBaseUrl");
//        this.userName = userName;
//        this.pass = pass;
//        baseUrl = PropertyFileReader.getValue();
//    }

//    @Test
//    public void test(){
//        YandexDiskPage yandexDiskPage = new YandexDiskPage();
//        yandexDiskPage.logIn(userName, pass, baseUrl);
//        assertTrue(yandexDiskPage.isLoginConfirm(), "Couldn't login to the page");
//
//        DragDropPage dragDropPage = new DragDropPage().dragAndDrop();
//        Assert.assertTrue(dragDropPage.isProgressbarPresented(), "No such element presented");
//
//        RestoreElementPage restoreElementPage = new RestoreElementPage();
//        restoreElementPage.restoreElement();
//
//        yandexDiskPage.lofOff();
//        assertTrue(yandexDiskPage.isLogOutConfirm(), "Couldn't log out from the site");
//    }

    @AfterClass
    public void tearDown() throws Exception {
        WebDriverSingleton.kill();
    }
}
