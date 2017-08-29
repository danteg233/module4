package tests.git.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;

public class GitLogInPage extends AbstractPage {

    private static final By SIGN_UP_BUTTON = By.xpath("//*[@href='/login']");
    private static final By LOGIN_LOCATOR = By.xpath(".//*[@name='login']");
    private static final By PASS_LOCATOR = By.xpath(".//*[@name='password']");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOCATOR = By.xpath(".//*[@class='dashboard-sidebar column one-third']");


    public GitLogInPage logIn(String userName, String pass){
        click(SIGN_UP_BUTTON);
        clickAndSendText(LOGIN_LOCATOR, userName);
        clickAndSendText(PASS_LOCATOR, pass);
        click(SUBMIT_BUTTON);
        return this;
    }

    public boolean logInConfirm(){
        return isElementPresented(CONFIRM_LOCATOR);
    }

}
