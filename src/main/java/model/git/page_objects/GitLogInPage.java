package tests.git;

import core.AbstractPage;
import org.openqa.selenium.By;

public class GitLogInPage extends AbstractPage {

    private static final By SIGN_UP_BUTTON = By.xpath("//*[@href='/login']");
    private static final By LOGIN_LOCATOR = By.xpath(".//*[@name='login']");
    private static final By PASS_LOCATOR = By.xpath(".//*[@name='password']");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOCATOR = By.xpath(".//*[@class='dashboard-sidebar column one-third']");


    public GitLogInPage logIn(String baseUrl, String userName, String pass){
        browser.goTo(baseUrl);
        browser.click(SIGN_UP_BUTTON);
        browser.clickAndSendText(LOGIN_LOCATOR, userName);
        browser.clickAndSendText(PASS_LOCATOR, pass);
        browser.click(SUBMIT_BUTTON);
        return this;
    }

    public boolean logInConfirm(){
        return browser.isElementPresented(CONFIRM_LOCATOR);
    }

}
