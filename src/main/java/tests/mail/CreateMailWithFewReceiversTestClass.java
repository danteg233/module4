package tests.mail;

import model.business_objects.EMail;
import model.business_objects.StaticEmailFactory;
import org.testng.annotations.*;
import reporting.MyLogger;
import model.mail.page_objects.MailPage;

import static org.testng.Assert.assertTrue;

public class CreateMailWithFewReceiversTestClass extends MainMailTestClass {

    @Test
    @Parameters({"fewReceivers", "subj", "context"})
    public void createMailWithFewReceiversTest(String fewReceivers, String subj, String context){
        MyLogger.info("---CREATING EMAIL WITH FEW RECEIVERS---");
        EMail email = StaticEmailFactory.getEmailWithAllFields(fewReceivers, subj, context);
        assertTrue(new MailPage().makeDraft(email).sendEmail().isSentLocatorPresented());
    }
}
