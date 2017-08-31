package model.mail.page_objects;

import core.AbstractPage;
import core.Browser;
import org.openqa.selenium.By;
import model.business_objects.EMail;
import utils.UtilsClass;
import reporting.MyLogger;


public class MailPage extends AbstractPage {
    private static final By SENT_FOLDER = By.xpath(".//*[@href='#sent']");
    private static final By SENT_FOLDER_EMPTY = By.xpath(".//*[@data-key='box=messages-empty-box']");
    private static final By SELECT_EMAIL_BOX = By.xpath(".//*[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']");
    private static final By DELETE_BUTTON = By.xpath(".//*[@class='svgicon svgicon-mail--MainToolbar-Delete']");
    private static final By PROFILE_BUTTON = By.xpath(".//div[contains(@class, 'ns-view-head-user')]");
    private static final By LOG_OUT_BUTTON = By.xpath(".//*[@class='b-mail-dropdown__item'][last()]");
    private static final By LOG_OUT_CHECK = By.xpath("//*[@class='home-logo__default']");
    private static final By SEND_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By EMAIL_SENT_LOCATOR = By.xpath(".//*[@class='mail-Done js-done']");
    private static final By WRITE_BUTTON_LOCATOR = By.xpath(".//div[@class='mail-ComposeButton-Wrap']//a[@href='#compose']");
    private static final By TO_LOCATOR = By.xpath("//div[@name='to']");
    private static final By SUBJ_LOCATOR = By.xpath("//input[@name='subj']");
    private static final By TEXT_LOCATOR = By.xpath("//div[contains(@role, 'textbox')]");
    private static final By DRAFT_BUTTON = By.xpath(".//*[@href='#draft']");
    private static final By SAVE_CHANGES_POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-i']");
    private static final By SAVE_BUTTON = By.xpath(".//*[@data-action='save']");
    private static final By ERROR_LOCATOR = By.xpath(".//*[contains(@class, 'ns-view-compose-field-to-error')]");

    public MailPage toSentFolder(){
        browser.click(SENT_FOLDER);
        return this;
    }

    public MailPage deleteEmail(EMail eMail){
        UtilsClass.assertEmail(eMail, Browser.getWebDriver());
        browser.click(SELECT_EMAIL_BOX);
        browser.click(DELETE_BUTTON);
        return this;
    }

    public MailPage logOff(){
        try{
            browser.scrollToElement(PROFILE_BUTTON);
            browser.click(PROFILE_BUTTON);
            browser.click(LOG_OUT_BUTTON);
        }catch (Exception e){
            MyLogger.error(e.getMessage());
            Browser.kill();
        }
        return this;
    }

    public MailPage sendEmail(){
        browser.click(SEND_BUTTON);
        return this;
    }

    public MailPage isEmailExist(EMail eMail) throws Exception {
        By temp = UtilsClass.assertEmail(eMail, Browser.getWebDriver());
        if (temp==null){
            MyLogger.error("No email with this parameters");
            throw new Exception("No email with this parameters");
        }
        browser.click(temp);
        return this;
    }

    public MailPage makeDraft(EMail eMail){
        browser.scrollToElement(WRITE_BUTTON_LOCATOR);
        browser.click(WRITE_BUTTON_LOCATOR);
        browser.clickAndSendText(TO_LOCATOR, eMail.getTo());
        browser.clickAndSendText(TEXT_LOCATOR, eMail.getText());
        browser.clickAndSendText(SUBJ_LOCATOR, eMail.getObj());
        return this;
    }

    public MailPage goToDraftFolder(){
        browser.click(DRAFT_BUTTON);
        if(browser.isElementPresented(SAVE_CHANGES_POP_UP_MENU)){
            browser.click(SAVE_BUTTON);
        }
        return this;
    }

    public boolean isErrorPresented(){
        return browser.isElementPresented(ERROR_LOCATOR);
    }
    public boolean isSentLocatorPresented(){
        return browser.isElementPresented(EMAIL_SENT_LOCATOR);
    }
    public boolean isSentFolderEmpty(){
        return browser.isElementPresented(SENT_FOLDER_EMPTY);
    }
    public boolean isLogOutCheckPresented(){
        return browser.isElementPresented(LOG_OUT_CHECK);
    }
}
