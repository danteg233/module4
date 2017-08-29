package tests.mail.page_objects;

import core.AbstractPage;
import core.Browser;
import org.openqa.selenium.By;
import tests.mail.business_objects.EMail;
import tests.mail.utils.UtilsClass;
import reporting.MyLogger;


public class MailPage extends AbstractPage {
    private static final By SENT_FOLDER = By.xpath(".//*[@href='#sent']");
    private static final By SENT_FOLDER_EMPTY = By.xpath(".//*[@data-key='box=messages-empty-box']");
    private static final By SELECT_EMAIL_BOX = By.xpath(".//*[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']");
    private static final By DELETE_BUTTON = By.xpath(".//*[@class='svgicon svgicon-mail--MainToolbar-Delete']");
    private static final By PROFILE_BUTTON = By.xpath(".//div[contains(@class, 'ns-view-head-user')]");
    private static final By LOG_OUT_BUTTON = By.xpath(".//*[@class='b-mail-dropdown__item'][last()]");
    private static final By LOG_OUT_CHECK = By.xpath("//*[@class='home-logo__default']");

    public void toSentFolder(EMail eMail){
        browser.click(SENT_FOLDER);
        UtilsClass.assertEmail(eMail, Browser.getWebDriver());
        browser.click(SELECT_EMAIL_BOX);
        browser.click(DELETE_BUTTON);
    }

    public void logOff(){
        try{
            browser.click(PROFILE_BUTTON);
            browser.click(LOG_OUT_BUTTON);
        }catch (Exception e){
            MyLogger.error(e.getMessage());
            Browser.kill();
        }
    }

    public boolean isSentFolderEmpty(){
        return browser.isElementPresented(SENT_FOLDER_EMPTY);
    }
    public boolean isLogOutCheckPresented(){
        return browser.isElementPresented(LOG_OUT_CHECK);
    }
}
