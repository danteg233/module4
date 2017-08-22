package tests.mail.business_objects;

import org.testng.annotations.Parameters;

public class EMail {
    private String to;
    private String obj;
    private String text;

    @Parameters({"to", "obj", "text"})
    public EMail(String to, String obj, String text){
        this.to = to;
        this.obj = obj;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public String getObj() {
        return obj;
    }

    public String getText() {
        return text;
    }
}
