package steps.mail;

import core.WebDriverSingleton;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import tests.yandex.business_objects.YandexDiskPage;

import static org.testng.Assert.assertTrue;

public class YandexDiskSteps {
    private YandexDiskPage yandexDiskPage;

    @Given("^I have main login page$")
    public void iHaveMainLoginPage(){
        yandexDiskPage = new YandexDiskPage();
    }

    @And("^I enter ([^\"]*) and ([^\"]*) and click login button$")
    public void iEnterUserNameAndPassword(String userName, String password) throws Throwable {
        yandexDiskPage = new YandexDiskPage().logIn(userName, password);
    }

    @Then("^I should see mail page$")
    public void iShouldSeeMailPage(){
        assertTrue(yandexDiskPage.isLoginConfirm(), "Couldn't login to the page");
    }



}
