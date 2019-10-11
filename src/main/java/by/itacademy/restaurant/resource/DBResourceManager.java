package by.itacademy.restaurant.resource;

import java.util.ResourceBundle;

public class DBResourceManager {

    private final static DBResourceManager instance = new DBResourceManager();

    private final static ResourceBundle bundle = ResourceBundle.getBundle("db");

    private DBResourceManager() {
    }

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
