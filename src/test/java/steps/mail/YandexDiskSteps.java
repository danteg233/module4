package steps.mail;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import model.yandex.page_objects.LogInPage;
import model.yandex.page_objects.YandexDiskPage;

import static org.testng.Assert.assertTrue;

public class YandexDiskSteps {
    private LogInPage logInPage;

    @Given("^I have main login page$")
    public void iHaveMainLoginPage(){
        logInPage = new LogInPage();
    }

    @And("^I enter ([^\"]*) and ([^\"]*) and click login button$")
    public void iEnterUserNameAndPassword(String userName, String password){
        logInPage.logIn(userName, password);
    }

    @Then("^I should see mail page$")
    public void iShouldSeeMailPage(){
        assertTrue(logInPage.isLoginConfirm(), "Couldn't login to the page");
    }

}
