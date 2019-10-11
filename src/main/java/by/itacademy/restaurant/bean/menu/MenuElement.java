package by.itacademy.restaurant.bean.menu;

public abstract class MenuElement {

    private int id;
    private String name;
    private String type;
    private float price;
    private float caloricValue;
    private boolean isAvailable;


    public MenuElement(String name, String type, float price, boolean isAvailable) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCaloricValue() {
        return caloricValue;
    }

    public void setCaloricValue(float caloricValue) {
        this.caloricValue = caloricValue;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuElement that = (MenuElement) o;

        if (Float.compare(that.price, price) != 0) return false;
        if (Float.compare(that.caloricValue, caloricValue) != 0) return false;
        if (isAvailable != that.isAvailable) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (caloricValue != +0.0f ? Float.floatToIntBits(caloricValue) : 0);
        result = 31 * result + (isAvailable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", caloricValue=" + caloricValue +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
