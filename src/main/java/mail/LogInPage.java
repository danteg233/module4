package mail;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LogInPage extends AbstractPage {
    private static final By LOGIN_LOCATOR = By.name("login");
    private static final By PASS_LOCATOR = By.name("passwd");
    private static final By SUBMIT_LOCATOR = By.xpath(".//*[@type='submit']");
    private static final By ERROR_PAGE = By.xpath(".//*[@class = 'passport-Domik-Form-Error passport-Domik-Form-Error_active']");

    public MailPage logIn(String login, String pass, String baseUrl) throws Exception {
        webDriver.get(baseUrl);
        waitForElementVisible(LOGIN_LOCATOR);
        webDriver.findElement(LOGIN_LOCATOR).click();
        webDriver.findElement(LOGIN_LOCATOR).sendKeys(login);
        webDriver.findElement(PASS_LOCATOR).click();
        webDriver.findElement(PASS_LOCATOR).sendKeys(pass);
        webDriver.findElement(SUBMIT_LOCATOR).click();
        Assert.assertFalse(isElementPresented(ERROR_PAGE));
        return new MailPage();

    }
}
