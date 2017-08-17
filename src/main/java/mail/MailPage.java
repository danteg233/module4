package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class MailPage extends mail.AbstractPage {


    private static final By WRITE_BUTTON_LOCATOR = By.xpath(".//*[@title=\"Написать (w, c)\"]");
    private static final By TO_LOCATOR = By.xpath("//div[@name='to']");
    private static final By SUBJ_LOCATOR = By.xpath("//input[@name='subj']");
    private static final By TEXT_LOCATOR = By.xpath("//div[contains(@role, 'textbox')]");
    private static final By DRAFT_BUTTON = By.xpath("//*[contains(@title,'Черновики')]");
    private static final By SAVE_CHANGES_POP_UP_MENU = By.xpath(".//*[@class='_nb-popup-i']");
    private static final By SAVE_BUTTON = By.xpath(".//*[@data-action='save']");
    private static final By SEND_BUTTON = By.xpath(".//*[contains(@title, 'Отправить письмо')]");
    private static final By SEND_FOLDER = By.xpath(".//*[contains(@title, 'Отправленные')]");
    private static final By LOGIN = By.xpath(".//*[@class='mail-User-Name']");

    private String to, subj, context;

    public MailPage(WebDriver webDriver) {
        super(webDriver);
    }


    public MailPage makeDraft(String to, String subj,String context){
        this.to = to;
        this.subj = subj;
        this.context = context;
        webDriver.findElement(WRITE_BUTTON_LOCATOR).click();
        waitForElementPresent(TO_LOCATOR);
        webDriver.findElement(TO_LOCATOR).sendKeys(to);
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



    public MailPage sendEmail(){
        By temp = assertEmail();
        waitForElementPresent(temp);
        webDriver.findElement(temp).click();
        try{
            webDriver.findElement(SEND_BUTTON).click();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return this;
    }

    public void toSendFolder(){
        webDriver.findElement(SEND_FOLDER).click();
        assertEmail();
        logOff();
    }

    private void logOff(){
        try{
            String  temp = webDriver.findElement(By.xpath(".//*[contains(@title, '" + webDriver.findElement(LOGIN).getText() + "')]")).getAttribute("title");
            webDriver.findElement(By.xpath(".//*[@title='" + temp + "'" + "]")).click();
            webDriver.findElement(By.xpath(".//*[@data-metric='Меню сервисов:Выход']")).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
            webDriver.quit();
        }
    }

    private By assertEmail(){
        assertEquals(webDriver.findElement(By.xpath(".//*[@title='" + to + "']")).getAttribute("title"), to);
        assertEquals(webDriver.findElement(By.xpath(".//*[@title='" + subj + "']")).getText(), subj);
        assertEquals(webDriver.findElement(By.xpath(".//*[@title='" + context + "']")).getText(), context);
        return By.xpath(".//*[@class ='mail-MessageSnippet-Wrapper']");
    }

    //    private void assertEmail(String to, String subj, String context){
//        assertEquals(webDriver.findElement(SUBJ_LOCATOR).getAttribute("value"), subj);
//        assertEquals(webDriver.findElement(TEXT_LOCATOR).getText(), context);
//        assertEquals(webDriver.findElement(TO_LOCATOR).findElement(By.xpath("//*[@class='js-bubble mail-Bubble']")).getAttribute("data-contact-email"), to);
//    }


}
