package tests.mail.page_objects;

import core.AbstractPage;
import core.Browser;
import org.openqa.selenium.By;
import tests.mail.business_objects.EMail;
import tests.mail.utils.UtilsClass;
import reporting.MyLogger;

public class SendEmailPage extends AbstractPage {

    private static final By SEND_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By EMAIL_SENT_LOCATOR = By.xpath(".//*[@class='mail-Done js-done']");

    public SendEmailPage sendEmail(EMail eMail) throws Exception {
        By temp = UtilsClass.assertEmail(eMail, Browser.getWebDriver());
        if (temp==null){
            MyLogger.error("No email with this parameters");
            throw new Exception("No email with this parameters");
        }
        browser.click(temp);
        try{
            browser.click(SEND_BUTTON);
        }catch (Exception e){
            MyLogger.error(e.getMessage());
        }
        return this;
    }

    public boolean isSentLocatorPresented(){
        return browser.isElementPresented(EMAIL_SENT_LOCATOR);
    }
}
