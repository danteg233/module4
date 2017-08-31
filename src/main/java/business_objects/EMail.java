package business_objects;

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

    public String getTo() {
        return to;
    }

    public String getObj() {
        return obj;
    }

    public String getText() {
        return text;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public void setText(String text) {
        this.text = text;
    }
}
