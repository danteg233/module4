package tests.mail;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import tests.mail.business_objects.EMail;

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
    private static final By EMAIL_SEND_LOCATOR = By.xpath(".//*[@class='mail-Done js-done']");
    private static final By SEND_FOLDER_EMPTY = By.xpath(".//*[@data-key='box=messages-empty-box']");
    private static final By SELECT_EMAIL_BOX = By.xpath(".//*[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']");
    private static final By DELETE_BUTTON = By.xpath(".//*[@class='svgicon svgicon-mail--MainToolbar-Delete']");
    private static final By PROFILE_BUTTON = By.xpath(".//div[contains(@class, 'ns-view-head-user')]");
    private static final By LOG_OUT_BUTTON = By.xpath(".//*[@class='b-mail-dropdown__item'][last()]");
    private static final By LOG_OUT_CHECK = By.xpath("//*[@class='home-logo__default']");
    private static final By DISK_LOCATOR = By.xpath(".//*[@data-metrika-id='disk']");

    private String to, subj, context;


    public MailPage makeDraft(EMail eMail){
        this.to = eMail.getTo();
        this.subj = eMail.getObj();
        this.context = eMail.getText();
        webDriver.findElement(WRITE_BUTTON_LOCATOR).click();
        waitForElementPresent(TO_LOCATOR);
        webDriver.findElement(TO_LOCATOR).sendKeys(to);
        webDriver.findElement(SUBJ_LOCATOR).click();
        webDriver.findElement(SUBJ_LOCATOR).sendKeys(subj);
        webDriver.findElement(TEXT_LOCATOR).click();
        webDriver.findElement(TEXT_LOCATOR).sendKeys(context);
        webDriver.findElement(DRAFT_BUTTON).click();
        if(isElementPresented(SAVE_CHANGES_POP_UP_MENU)){ //TODO: SHOULD THIS CONDITION BE HERE
            webDriver.findElement(SAVE_BUTTON).click();
        }
        return this;
    }

    public MailPage sendEmail(){
        By temp = assertEmail();
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

    public void toSentFolder(){
        Assert.assertTrue(isElementPresented(EMAIL_SEND_LOCATOR));
        waitForElementVisible(SENT_FOLDER);
        new Actions(webDriver).click(webDriver.findElement(SENT_FOLDER)).build().perform();
        assertEmail();
        waitForElementEnabled(SELECT_EMAIL_BOX);
        webDriver.findElement(SELECT_EMAIL_BOX).click();
        waitForElementEnabled(DELETE_BUTTON);
        webDriver.findElement(DELETE_BUTTON).click();
        Assert.assertTrue(isElementPresented(SEND_FOLDER_EMPTY));

    }

    public void logOff(){
        try{
            WebElement button = webDriver.findElement(PROFILE_BUTTON);
            new Actions(webDriver).click(button).build().perform();
            waitForElementEnabled(LOG_OUT_BUTTON);
            webDriver.findElement(LOG_OUT_BUTTON).click();
            Assert.assertTrue(isElementPresented(LOG_OUT_CHECK));
        }catch (Exception e){
            System.out.println(e.getMessage());
            webDriver.quit();
        }
    }

    private By assertEmail(){
        //TODO : change
        assertEquals(webDriver.findElement(By.xpath(".//*[@title='" + to + "']")).getAttribute("title"), to);
        assertEquals(webDriver.findElement(By.xpath(".//*[@title='" + subj + "']")).getText(), subj);
        assertEquals(webDriver.findElement(By.xpath(".//*[@title='" + context + "']")).getText(), context);
        return By.xpath(".//*[@class='mail-MessageSnippet-Content']");
    }

    public void toDisk(){
        new Actions(webDriver).click(webDriver.findElement(DISK_LOCATOR)).build().perform();
    }


}
