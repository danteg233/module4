package tests.yandex.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;

public class YandexDiskPage extends AbstractPage {

    private static final By LOGIN_LOCATOR = By.xpath(".//*[@name='login']");
    private static final By PASS_LOCATOR = By.xpath(".//*[@name='password']");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOGIN = By.xpath(".//*[@class='upload-button__attach']");
    private static final By POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-content']");
    private static final By CLOSE_BUTTON = By.xpath(".//a[@class='_nb-popup-close js-dialog-close']");
    private static final By PROFILE_TOOLBAR = By.xpath(".//*[@class='header__user']");
    private static final By LOG_OUT_BUTTON = By.xpath(".//div[@class='popup2 popup2_theme_normal popup2_direction_bottom-right popup2_visible_yes popup2_target_anchor popup2_autoclosable']//*[@class='menu__list-item'][last()]");
    private static final By LOG_OUT_CONFIRM = By.xpath("//*[@class='home-logo__default']");

    public YandexDiskPage logIn(String login, String pass){
        browser.clickAndSendText(LOGIN_LOCATOR, login);
        browser.clickAndSendText(PASS_LOCATOR, pass);
        browser.click(SUBMIT_BUTTON);
        return this;
    }

    public YandexDiskPage lofOff(){
        browser.click(PROFILE_TOOLBAR);
        browser.click(LOG_OUT_BUTTON);
        return this;
    }
    public boolean isLoginConfirm(){
        return browser.isElementPresented(CONFIRM_LOGIN);
    }
    public boolean isLogOutConfirm(){return browser.isElementPresented(LOG_OUT_CONFIRM);}
}
