package steps.mail;

import core.WebDriverSingleton;
import cucumber.api.java.en.Given;
import reporting.MyLogger;


public class BackgroundSteps {
    @Given("^I have YandexDisk page as \"([^\"]*)\"$")
    public void iHaveYandexDiskPageAs(String url) throws Throwable {
        MyLogger.info("---CUCUMBER TEST---");
        WebDriverSingleton.goTo(url);
    }
}
