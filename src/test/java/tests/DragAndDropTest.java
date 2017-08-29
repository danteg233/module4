package tests;

import core.Browser;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.ITestResult;
import org.testng.annotations.*;

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
