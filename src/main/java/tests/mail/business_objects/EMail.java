package tests.mail.business_objects;

public class EMail {
    private String to;
    private String obj;
    private String text;

    public EMail(String to, String obj, String text) {
        this.to = to;
        this.obj = obj;
        this.text = text;
    }

    public EMail() {
    }

    public EMail(String obj, String text) {
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
