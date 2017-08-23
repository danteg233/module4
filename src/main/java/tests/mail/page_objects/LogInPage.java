package tests.mail.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;


public class LogInPage extends AbstractPage {
    private static final By LOGIN_LOCATOR = By.name("login");
    private static final By PASS_LOCATOR = By.name("passwd");
    private static final By SUBMIT_LOCATOR = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOGIN = By.xpath(".//*[@class='mail-App-Header']");

    public LogInPage logIn(String login, String pass, String baseUrl) throws Exception {
        webDriver.get(baseUrl);
        waitForElementVisible(LOGIN_LOCATOR);
        webDriver.findElement(LOGIN_LOCATOR).click();
        webDriver.findElement(LOGIN_LOCATOR).sendKeys(login);
        webDriver.findElement(PASS_LOCATOR).click();
        webDriver.findElement(PASS_LOCATOR).sendKeys(pass);
        webDriver.findElement(SUBMIT_LOCATOR).click();
        return this;
    }

    public boolean isLoginConfirm(){
        return isElementPresented(CONFIRM_LOGIN);
    }

}
