package tests.mail.business_objects;

public class EmailFactory {

    public static EMail getEmailWithoutTo(String subj, String context){
        return new EMail(subj, context);
    }

    public static EMail getEmailWithAllFields(String to, String subj, String context){
        return new EMail(to, subj, context);
    }

    public static EMail getEmailWithoutAnyField(){
        return new EMail();
    }

}
