package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import tests.yandex.business_objects.YandexDiskPage;

import static org.testng.Assert.assertTrue;

public class YandexDiskSteps {

    @Given("^I navigate to the login page as \"([^\"]*)\"$")
    public void iNavigateToTheLoginPageAs(String url) throws Throwable {
        YandexDiskPage yandexDiskPage = new YandexDiskPage().goTo(url);
    }

    @And("^I enter ([^\"]*) and ([^\"]*) and click login button$")
    public void iEnterUserNameAndPassword(String userName, String password) throws Throwable {
        YandexDiskPage yandexDiskPage = new YandexDiskPage().logIn(userName, password);

    }

    @Then("^I should see mail page$")
    public void iShouldSeeMailPage(){
        assertTrue(new YandexDiskPage().isLoginConfirm(), "Couldn't login to the page");
    }


}
