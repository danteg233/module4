package tests.mail.page_objects;

import core.AbstractPage;
import core.ScreenShoter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import tests.mail.page_objects.MailPage;

import java.lang.ref.SoftReference;

public class YandexDiskPage extends AbstractPage {

    private static final By LOGIN_LOCATOR = By.xpath(".//*[@name='login']");
    private static final By PASS_LOCATOR = By.xpath(".//*[@name='password']");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By CONFIRM_LOGIN = By.xpath(".//*[@class='upload-button__attach']");
    private static final By DRAG_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][1]");
    private static final By DROP_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][last()]");
    private static final By PROGRESSBAR = By.xpath(".//*[@class='b-progressbar']");
    private static final By POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-content']");
    private static final By CLOSE_BUTTON = By.xpath(".//a[@class='_nb-popup-close js-dialog-close']");
    private static final By PROFILE_TOOLBAR = By.xpath(".//*[@class='header__user']");
    private static final By LOG_OUT_BUTTON = By.xpath(".//div[@class='popup2 popup2_theme_normal popup2_direction_bottom-right popup2_visible_yes popup2_target_anchor popup2_autoclosable']//*[@class='menu__list-item'][last()]");
    private static final By LOG_OUT_CONFIRM = By.xpath("//*[@class='home-logo__default']");

    public void logIn(String login, String pass, String baseUrl){
        webDriver.get(baseUrl);
        waitForElementEnabled(LOGIN_LOCATOR);
        new Actions(webDriver).click(webDriver.findElement(LOGIN_LOCATOR)).sendKeys(login).build().perform();
        new Actions(webDriver).click(webDriver.findElement(PASS_LOCATOR)).sendKeys(pass).build().perform();
        new Actions(webDriver).click(webDriver.findElement(SUBMIT_BUTTON)).build().perform();
    }

    public void lofOff(){
        waitForElementEnabled(PROFILE_TOOLBAR);
        new Actions(webDriver).click(webDriver.findElement(PROFILE_TOOLBAR)).build().perform();
        new Actions(webDriver).click(webDriver.findElement(LOG_OUT_BUTTON)).build().perform();
    }

    public void dragAndDrop(){
        highlightElement(DRAG_LOCATOR);
        highlightElement(DROP_LOCATOR);
        ScreenShoter.takeScreenshot();
        unHighlightElement(DROP_LOCATOR);
        unHighlightElement(DRAG_LOCATOR);
        waitForElementVisible(DRAG_LOCATOR);
        waitForElementVisible(DROP_LOCATOR);
        new Actions(webDriver).dragAndDrop(webDriver.findElement(DRAG_LOCATOR), webDriver.findElement(DROP_LOCATOR)).build().perform();
    }

    public boolean isLoginConfirm(){
        return isElementPresented(CONFIRM_LOGIN);
    }
    public boolean isLogOutConfirm(){return isElementPresented(LOG_OUT_CONFIRM);}
    public boolean isProgressbarPresented(){
        return isElementPresented(PROGRESSBAR);
    }

    public MailPage toMail() throws InterruptedException {
        JavascriptExecutor jsEx = (JavascriptExecutor) webDriver;
        jsEx.executeScript("document.evaluate(\".//a[contains(@href, 'mail.yandex.')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
        return new MailPage();
    }



}
