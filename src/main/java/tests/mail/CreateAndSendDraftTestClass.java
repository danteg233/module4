package tests.mail;

import model.business_objects.EMail;
import model.business_objects.StaticEmailFactory;
import org.testng.annotations.*;
import reporting.MyLogger;
import model.mail.page_objects.MailPage;

import static org.testng.Assert.assertTrue;

public class CreateAndSendDraftTestClass extends MainMailTestClass {

    @Test
    @Parameters({"to", "subj", "context"})
    public void createAndSendDraftTest(String to, String subj, String context) throws Exception {
        EMail email = StaticEmailFactory.getEmailWithAllFields(to, subj, context);
        MyLogger.info("---CREATING A DRAFT---");
        assertTrue(new MailPage().makeDraft(email).goToDraftFolder().isEmailExist(email).sendEmail().isSentLocatorPresented());
        MyLogger.info("---DELETING DRAFT---");
        assertTrue(new MailPage().toSentFolder().deleteEmail(email).isSentFolderEmpty());
    }

}
