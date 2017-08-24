package tests.mail.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tests.mail.business_objects.EMail;
import tests.mail.utils.UtilsClass;


public class MailPage extends AbstractPage {
    private static final By SENT_FOLDER = By.xpath(".//*[@href='#sent']");
    private static final By SENT_FOLDER_EMPTY = By.xpath(".//*[@data-key='box=messages-empty-box']");
    private static final By SELECT_EMAIL_BOX = By.xpath(".//*[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']");
    private static final By DELETE_BUTTON = By.xpath(".//*[@class='svgicon svgicon-mail--MainToolbar-Delete']");
    private static final By PROFILE_BUTTON = By.xpath(".//div[contains(@class, 'ns-view-head-user')]");
    private static final By LOG_OUT_BUTTON = By.xpath(".//*[@class='b-mail-dropdown__item'][last()]");
    private static final By LOG_OUT_CHECK = By.xpath("//*[@class='home-logo__default']");

    public void toSentFolder(EMail eMail){
        waitForElementEnabled(SENT_FOLDER);
        new Actions(webDriver).click(webDriver.findElement(SENT_FOLDER)).build().perform();
        UtilsClass.assertEmail(eMail, webDriver);
        waitForElementEnabled(SELECT_EMAIL_BOX);
        webDriver.findElement(SELECT_EMAIL_BOX).click();
        waitForElementEnabled(DELETE_BUTTON);
        webDriver.findElement(DELETE_BUTTON).click();
    }

    public void logOff(){
        try{
            WebElement button = webDriver.findElement(PROFILE_BUTTON);
            new Actions(webDriver).click(button).build().perform();
            waitForElementEnabled(LOG_OUT_BUTTON);
            webDriver.findElement(LOG_OUT_BUTTON).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
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
