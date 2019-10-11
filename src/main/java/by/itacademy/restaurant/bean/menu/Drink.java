package by.itacademy.restaurant.bean.menu;

public class Drink extends MenuElement {

    private float volume;

    public Drink(String name, String type, float price, boolean isAvailable, float volume) {
        super(name, type, price, isAvailable);
        this.volume = volume;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drink drink = (Drink) o;

        return Float.compare(drink.volume, volume) == 0;
    }

    @Override
    public int hashCode() {
        return (volume != +0.0f ? Float.floatToIntBits(volume) : 0);
    }

    @Override
    public String toString() {
        return super.toString() +
                getClass().getSimpleName() +
                "volume=" + volume +
                '}';
    }
}
