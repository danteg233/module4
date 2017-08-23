package tests.mail;

import core.AbstractPage;
import core.ScreenShoter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class YandexDiskPage extends AbstractPage {

    private static final By DRAG_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][1]");
    private static final By DROP_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][last()]");
    private static final By PROGRESSBAR = By.xpath(".//*[@class='b-progressbar']");
    private static final By POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-content']");
    private static final By CLOSE_BUTTON = By.xpath(".//a[@class='_nb-popup-close js-dialog-close']");


    public void dragAndDrop(){
//        try{
//            if (isElementPresented(POP_UP_MENU)){
//                new Actions(webDriver).click(webDriver.findElement(CLOSE_BUTTON)).build().perform();
//            }
//        }catch (Exception e){
//
//        }
        highlightElement(DRAG_LOCATOR);
        highlightElement(DROP_LOCATOR);
        ScreenShoter.takeScreenshot();
        unHighlightElement(DROP_LOCATOR);
        unHighlightElement(DRAG_LOCATOR);
        waitForElementVisible(DRAG_LOCATOR);
        waitForElementVisible(DROP_LOCATOR);
        new Actions(webDriver).dragAndDrop(webDriver.findElement(DRAG_LOCATOR), webDriver.findElement(DROP_LOCATOR)).build().perform();
    }

    public boolean isProgressbarPresented(){
        return isElementPresented(PROGRESSBAR);
    }

    public MailPage toMail() throws InterruptedException {
        JavascriptExecutor jsEx = (JavascriptExecutor) webDriver;
        jsEx.executeScript("document.evaluate(\".//a[contains(@href, 'mail.yandex.')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
        return new MailPage();
    }



}
