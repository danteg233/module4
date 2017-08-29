package tests.mail.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
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
        browser.click(WRITE_BUTTON_LOCATOR);
        browser.clickAndSendText(TO_LOCATOR, eMail.getTo());
        browser.clickAndSendText(SUBJ_LOCATOR, eMail.getObj());
        browser.clickAndSendText(TEXT_LOCATOR, eMail.getText());
        browser.click(DRAFT_BUTTON);
        if(browser.isElementPresented(SAVE_CHANGES_POP_UP_MENU)){
            browser.click(SAVE_BUTTON);
        }
        return new MailPage();
    }
}
