package steps.mail;

import core.AbstractPage;
import core.Browser;
import cucumber.api.java.en.Given;
import reporting.MyLogger;


public class BackgroundSteps extends AbstractPage{
    @Given("^I have YandexDisk page as \"([^\"]*)\"$")
    public void iHaveYandexDiskPageAs(String url) throws Throwable {
        MyLogger.info("---CUCUMBER TEST---");
        browser.goTo(url);
    }
}
