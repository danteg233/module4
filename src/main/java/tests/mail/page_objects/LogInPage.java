package tests.mail.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
import reporting.MyLogger;


public class LogInPage extends AbstractPage {
    private static final By LOGIN_LOCATOR = By.name("login");
    private static final By PASS_LOCATOR = By.name("passwd");
    private static final By SUBMIT_LOCATOR = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOGIN = By.xpath(".//*[@class='mail-App-Header']");

    public LogInPage logIn(String login, String pass, String baseUrl) throws Exception {
        MyLogger.info("Going to " + baseUrl);
        webDriver.get(baseUrl);
        clickAndSendText(LOGIN_LOCATOR, login);
        clickAndSendText(PASS_LOCATOR, pass);
        click(SUBMIT_LOCATOR);
        return this;
    }

    public boolean isLoginConfirm(){
        return isElementPresented(CONFIRM_LOGIN);
    }

}
