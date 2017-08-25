package tests.mail.page_objects;

import core.AbstractPage;
import core.ScreenShoter;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import tests.mail.business_objects.EMail;

public class DraftPage extends AbstractPage {

    private static final By WRITE_BUTTON_LOCATOR = By.xpath(".//div[@class='mail-ComposeButton-Wrap']//a[@href='#compose']");
    private static final By TO_LOCATOR = By.xpath("//div[@name='to']");
    private static final By SUBJ_LOCATOR = By.xpath("//input[@name='subj']");
    private static final By TEXT_LOCATOR = By.xpath("//div[contains(@role, 'textbox')]");
    private static final By DRAFT_BUTTON = By.xpath(".//*[@href='#draft']");
    private static final By SAVE_CHANGES_POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-i']");
    private static final By SAVE_BUTTON = By.xpath(".//*[@data-action='save']");



    public MailPage makeDraft(EMail eMail){
        waitForElementEnabled(WRITE_BUTTON_LOCATOR);
        webDriver.findElement(WRITE_BUTTON_LOCATOR).click();
        waitForElementPresent(TO_LOCATOR);
        new Actions(webDriver).click(webDriver.findElement(TO_LOCATOR)).sendKeys(eMail.getTo()).build().perform();
        webDriver.findElement(SUBJ_LOCATOR).click();
        webDriver.findElement(SUBJ_LOCATOR).sendKeys(eMail.getObj());
        webDriver.findElement(TEXT_LOCATOR).click();
        webDriver.findElement(TEXT_LOCATOR).sendKeys(eMail.getText());
        webDriver.findElement(DRAFT_BUTTON).click();
        if(isElementPresented(SAVE_CHANGES_POP_UP_MENU)){
            waitForElementEnabled(SAVE_BUTTON);
            new Actions(webDriver).click(webDriver.findElement(SAVE_BUTTON)).build().perform();
        }
        return new MailPage();
    }
}
