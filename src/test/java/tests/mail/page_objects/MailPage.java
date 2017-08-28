package tests.mail.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tests.mail.business_objects.EMail;
import tests.mail.utils.UtilsClass;
import tests.reporting.MyLogger;


public class MailPage extends AbstractPage {
    private static final By SENT_FOLDER = By.xpath(".//*[@href='#sent']");
    private static final By SENT_FOLDER_EMPTY = By.xpath(".//*[@data-key='box=messages-empty-box']");
    private static final By SELECT_EMAIL_BOX = By.xpath(".//*[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']");
    private static final By DELETE_BUTTON = By.xpath(".//*[@class='svgicon svgicon-mail--MainToolbar-Delete']");
    private static final By PROFILE_BUTTON = By.xpath(".//div[contains(@class, 'ns-view-head-user')]");
    private static final By LOG_OUT_BUTTON = By.xpath(".//*[@class='b-mail-dropdown__item'][last()]");
    private static final By LOG_OUT_CHECK = By.xpath("//*[@class='home-logo__default']");

    public void toSentFolder(EMail eMail){
        click(SENT_FOLDER);
        UtilsClass.assertEmail(eMail, webDriver);
        click(SELECT_EMAIL_BOX);
        click(DELETE_BUTTON);
    }

    public void logOff(){
        try{
            click(PROFILE_BUTTON);
            click(LOG_OUT_BUTTON);
        }catch (Exception e){
            MyLogger.error(e.getMessage());
            webDriver.quit();
        }
    }

    public boolean isSentFolderEmpty(){
        return isElementPresented(SENT_FOLDER_EMPTY);
    }
    public boolean isLogOutCheckPresented(){
        return isElementPresented(LOG_OUT_CHECK);
    }
}
