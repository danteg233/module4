package steps.mail;

import core.WebDriverSingleton;
import cucumber.api.java.en.Given;

public class BackgroundSteps {
    @Given("^I have YandexDisk page as \"([^\"]*)\"$")
    public void iHaveYandexDiskPageAs(String url) throws Throwable {
        WebDriverSingleton.goTo(url);
    }
}
