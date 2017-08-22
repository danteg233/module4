package git;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GitLogInPage extends AbstractPage {

    private static final By SIGN_UP_BUTTON = By.xpath("//*[@href='/login']");
    private static final By LOGIN_LOCATOR = By.xpath(".//*[@name='login']");
    private static final By PASS_LOCATOR = By.xpath(".//*[@name='password']");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOCATOR = By.xpath(".//*[@class='dashboard-sidebar column one-third']");


    public GitHubPage logIn(String userName, String pass){
        webDriver.findElement(SIGN_UP_BUTTON).click();
        webDriver.findElement(LOGIN_LOCATOR).sendKeys(userName);
        webDriver.findElement(PASS_LOCATOR).sendKeys(pass);
        webDriver.findElement(SUBMIT_BUTTON).click();
        Assert.assertTrue(isElementPresented(CONFIRM_LOCATOR));
        return new GitHubPage();
    }

}
