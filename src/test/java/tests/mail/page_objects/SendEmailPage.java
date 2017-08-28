package tests.mail.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
import tests.mail.business_objects.EMail;
import tests.mail.utils.UtilsClass;
import tests.reporting.MyLogger;

public class SendEmailPage extends AbstractPage {

    private static final By SEND_BUTTON = By.xpath(".//*[@type='submit']");
    private static final By EMAIL_SENT_LOCATOR = By.xpath(".//*[@class='mail-Done js-done']");

    public SendEmailPage sendEmail(EMail eMail) throws Exception {
        By temp = UtilsClass.assertEmail(eMail,webDriver);
        if (temp==null){
            MyLogger.error("No email with this parameters");
            throw new Exception("No email with this parameters");
        }
        click(temp);
        try{
            click(SEND_BUTTON);
        }catch (Exception e){
            MyLogger.error(e.getMessage());
        }
        return this;
    }

    public boolean isSentLocatorPresented(){
        return isElementPresented(EMAIL_SENT_LOCATOR);
    }
}
