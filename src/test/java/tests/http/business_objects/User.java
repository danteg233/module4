package tests.http.business_objects;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private String userName;
    private String email;
    private Map<String, Object> address = new HashMap<String, Object>();

    public User(int id, String name, String userName, String email, Map<String, Object> address) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Object> getAddress() {
        return address;
    }
}
