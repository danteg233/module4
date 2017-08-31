package tests.mail;

import model.business_objects.EMail;
import model.business_objects.StaticEmailFactory;
import org.testng.annotations.*;
import model.mail.page_objects.MailPage;

import static org.testng.Assert.assertTrue;

public class CreateEmptyMailTestClass extends MainMailTestClass {

    @Test
    public void createEmptyMailTest(){
        EMail email = StaticEmailFactory.getEmailWithoutAnyField();
        assertTrue(new MailPage().makeDraft(email).sendEmail().isErrorPresented());
    }

}
