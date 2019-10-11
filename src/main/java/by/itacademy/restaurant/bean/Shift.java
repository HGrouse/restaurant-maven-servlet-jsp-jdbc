package by.itacademy.restaurant.bean;

import java.util.Calendar;

public class Shift {

    private int id;
    private String address;
    private Calendar shiftStart;
    private Calendar shiftEnd;

    public Shift(int id, String address, Calendar shiftStart, Calendar shiftEnd) {
        this.id = id;
        this.address = address;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Calendar getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(Calendar shiftStart) {
        this.shiftStart = shiftStart;
    }

    public Calendar getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(Calendar shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shift shift = (Shift) o;

        if (id != shift.id) return false;
        if (address != null ? !address.equals(shift.address) : shift.address != null) return false;
        if (shiftStart != null ? !shiftStart.equals(shift.shiftStart) : shift.shiftStart != null) return false;
        return shiftEnd != null ? shiftEnd.equals(shift.shiftEnd) : shift.shiftEnd == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (shiftStart != null ? shiftStart.hashCode() : 0);
        result = 31 * result + (shiftEnd != null ? shiftEnd.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", shiftStart=" + shiftStart +
                ", shiftEnd=" + shiftEnd +
                '}';
    }
}
