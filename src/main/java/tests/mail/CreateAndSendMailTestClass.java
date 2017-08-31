package tests.mail;

import model.business_objects.EMail;
import model.business_objects.StaticEmailFactory;
import org.testng.annotations.*;
import model.mail.page_objects.MailPage;

import static org.testng.Assert.assertTrue;

public class CreateAndSendMailTestClass extends MainMailTestClass {

    @Test
    @Parameters({"to", "sameSubjAndText"})
    public void createAndSendMailTest(String to, String sameSubjAndText) throws Exception {
        EMail email = StaticEmailFactory.getEmailWithSameSubjAndText(to, sameSubjAndText);
        assertTrue(new MailPage().makeDraft(email).sendEmail().isSentLocatorPresented());
    }

}
