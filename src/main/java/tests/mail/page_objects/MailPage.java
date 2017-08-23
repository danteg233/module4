package tests.mail.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import tests.mail.business_objects.EMail;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class MailPage extends AbstractPage {
    private static final By WRITE_BUTTON_LOCATOR = By.xpath(".//div[@class='mail-ComposeButton-Wrap']//a[@href='#compose']");
    private static final By TO_LOCATOR = By.xpath("//div[@name='to']");
    private static final By SUBJ_LOCATOR = By.xpath("//input[@name='subj']");
    private static final By TEXT_LOCATOR = By.xpath("//div[contains(@role, 'textbox')]");
    private static final By DRAFT_BUTTON = By.xpath(".//*[@href='#draft']");
    private static final By SAVE_CHANGES_POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-i']");
    private static final By SAVE_BUTTON = By.xpath(".//*[@data-action='save']");
    private static final By SEND_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By SENT_FOLDER = By.xpath(".//*[@href='#sent']");
    private static final By EMAIL_SENT_LOCATOR = By.xpath(".//*[@class='mail-Done js-done']");
    private static final By SENT_FOLDER_EMPTY = By.xpath(".//*[@data-key='box=messages-empty-box']");
    private static final By SELECT_EMAIL_BOX = By.xpath(".//*[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']");
    private static final By DELETE_BUTTON = By.xpath(".//*[@class='svgicon svgicon-mail--MainToolbar-Delete']");
    private static final By PROFILE_BUTTON = By.xpath(".//div[contains(@class, 'ns-view-head-user')]");
    private static final By LOG_OUT_BUTTON = By.xpath(".//*[@class='b-mail-dropdown__item'][last()]");
    private static final By LOG_OUT_CHECK = By.xpath("//*[@class='home-logo__default']");

    private String to, subj, context;


    public MailPage makeDraft(EMail eMail){
        this.to = eMail.getTo();
        this.subj = eMail.getObj();
        this.context = eMail.getText();
        waitForElementEnabled(WRITE_BUTTON_LOCATOR);
        webDriver.findElement(WRITE_BUTTON_LOCATOR).click();
        waitForElementPresent(TO_LOCATOR);
        new Actions(webDriver).click(webDriver.findElement(TO_LOCATOR)).sendKeys(to).build().perform();
        webDriver.findElement(SUBJ_LOCATOR).click();
        webDriver.findElement(SUBJ_LOCATOR).sendKeys(subj);
        webDriver.findElement(TEXT_LOCATOR).click();
        webDriver.findElement(TEXT_LOCATOR).sendKeys(context);
        webDriver.findElement(DRAFT_BUTTON).click();
        if(isElementPresented(SAVE_CHANGES_POP_UP_MENU)){
            webDriver.findElement(SAVE_BUTTON).click();
        }
        return this;
    }

    public MailPage sendEmail(EMail eMail) throws Exception {
        By temp = assertEmail(eMail);
        if (temp==null){
            throw new Exception("No email with this parameters");
        }
        waitForElementPresent(temp);
        webDriver.findElement(temp).click();
        try{
            waitForElementEnabled(SEND_BUTTON);
            webDriver.findElement(SEND_BUTTON).click();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return this;
    }

    public void toSentFolder(EMail eMail) throws InterruptedException {
        waitForElementEnabled(SENT_FOLDER);
        new Actions(webDriver).click(webDriver.findElement(SENT_FOLDER)).build().perform();
        assertEmail(eMail);
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

    private By assertEmail(EMail eMail) throws InterruptedException {
        List<WebElement> tempList = webDriver.findElements(By.xpath(".//*[contains(@class,'ns-view-messages-item-wrap')]"));
        for(int i=1; i<=tempList.size(); i++){
            String temp = ".//*[contains(@class,'ns-view-messages-item-wrap')]" + '[' + i + ']';
            if(webDriver.findElement(By.xpath(temp + "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_sender js-message-snippet-sender']//*")).getAttribute("title").equalsIgnoreCase(eMail.getTo())
                    && webDriver.findElement(By.xpath(temp + "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']//*")).getAttribute("title").equalsIgnoreCase(eMail.getObj())
                    && webDriver.findElement(By.xpath(temp + "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']//*")).getAttribute("title").equalsIgnoreCase(eMail.getText())){
                return By.xpath(temp);
            }
        }
        return null;
    }

    public boolean isSentLocatorPresented(){
        return isElementPresented(EMAIL_SENT_LOCATOR);
    }

    public boolean isSentFolderEmpty(){
        return isElementPresented(SENT_FOLDER_EMPTY);
    }

    public boolean isLogOutCheckPresented(){
        return isElementPresented(LOG_OUT_CHECK);
    }
}
