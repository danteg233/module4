package steps.mail;

import core.WebDriverSingleton;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import tests.yandex.business_objects.YandexDiskPage;

public class BackgroundSteps {
    @Given("^I have YandexDisk page as \"([^\"]*)\"$")
    public void iHaveYandexDiskPageAs(String url) throws Throwable {
        WebDriverSingleton.goTo(url);
    }
}
