package by.itacademy.restaurant.bean.menu;

public class Dish extends MenuElement  {

    private float weight;

    public Dish(String name, String type, float price, boolean isAvailable, float weight) {
        super(name, type, price, isAvailable);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Dish dish = (Dish) o;

        return Float.compare(dish.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                getClass().getSimpleName() +
                "weight=" + weight +
                '}';
    }
}
