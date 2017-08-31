package model.yandex.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;

public class LogInPage extends AbstractPage{
    private static final By LOGIN_LOCATOR = By.xpath(".//*[@name='login']");
    private static final By PASS_LOCATOR = By.xpath(".//*[@name='password']");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOGIN = By.xpath(".//*[@class='upload-button__attach']");
    private static final By POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-content']");
    private static final By CLOSE_BUTTON = By.xpath(".//a[@class='_nb-popup-close js-dialog-close']");



    public LogInPage logIn(String login, String pass){
        browser.clickAndSendText(LOGIN_LOCATOR, login);
        browser.clickAndSendText(PASS_LOCATOR, pass);
        browser.click(SUBMIT_BUTTON);
        return this;
    }


    public boolean isLoginConfirm(){
        return browser.isElementPresented(CONFIRM_LOGIN);
    }
}
