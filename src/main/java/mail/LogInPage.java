package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LogInPage extends mail.AbstractPage {

    public LogInPage(WebDriver webDriver) {
        super(webDriver);
    }
    private static final By LOGIN_LOCATOR = By.name("login");
    private static final By PASS_LOCATOR = By.name("passwd");
    private static final By SUBMIT_LOCATOR = By.xpath(".//*[@type='submit']");
    private static final By ERROR_PAGE = By.xpath(".//*[@class = 'passport-Domik-Form-Error passport-Domik-Form-Error_active']");



    public MailPage logIn(String login, String pass, String baseUrl) throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(LOGIN_LOCATOR).click();
        webDriver.findElement(LOGIN_LOCATOR).sendKeys(login);
        webDriver.findElement(PASS_LOCATOR).click();
        webDriver.findElement(PASS_LOCATOR).sendKeys(pass);
        webDriver.findElement(SUBMIT_LOCATOR).click();
        if (isElementPresented(ERROR_PAGE)){
            throw new Exception("Wrong password or login!");
        }
        return new MailPage(webDriver);

    }
}
