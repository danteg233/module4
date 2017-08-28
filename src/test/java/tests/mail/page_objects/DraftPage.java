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
        click(WRITE_BUTTON_LOCATOR);
        clickAndSendText(TO_LOCATOR, eMail.getTo());
        clickAndSendText(SUBJ_LOCATOR, eMail.getObj());
        clickAndSendText(TEXT_LOCATOR, eMail.getText());
        click(DRAFT_BUTTON);
        if(isElementPresented(SAVE_CHANGES_POP_UP_MENU)){
            click(SAVE_BUTTON);
        }
        return new MailPage();
    }
}
