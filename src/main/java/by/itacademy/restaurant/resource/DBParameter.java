package by.itacademy.restaurant.resource;

public enum DBParameter {

    DRIVER_NAME ("db.driverName"),
    DB_URL ("db.url"),
    USERNAME ("db.username"),
    PASSWORD ("db.password"),
    POOL_SIZE ("db.poolSize");

    private String value;

    DBParameter(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
