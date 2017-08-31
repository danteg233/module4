package business_objects;

public class StaticEmailFactory {

    private static EMail createSimpleEmail(){
        EMail eMail = new EMail();
        eMail.setTo("noOne@mail.ru");
        eMail.setObj("nothing");
        eMail.setText("text");
        return eMail;
    }

    public static EMail getEmailWithoutTo(String subj, String context){
        EMail eMail = createSimpleEmail();
        eMail.setTo(" ");
        eMail.setObj(subj);
        eMail.setText(context);
        return eMail;
    }

    public static EMail getEmailWithOutSubj(String to, String context){
        EMail eMail = createSimpleEmail();
        eMail.setTo(to);
        eMail.setObj(" ");
        eMail.setText(context);
        return eMail;
    }

    public static EMail getEmailWithAllFields(String to, String subj, String context){
        return new EMail(to, subj, context);
    }


    public static EMail getEmailWithSameSubjAndText(String to, String text){
        EMail eMail = createSimpleEmail();
        eMail.setTo(to);
        eMail.setText(text);
        eMail.setObj(text);
        return eMail;
    }

    public static EMail getEmailWithoutAnyField(){
        EMail eMail = createSimpleEmail();
        eMail.setTo("");
        eMail.setText("");
        eMail.setObj("");
        return eMail;
    }

}
