package by.itacademy.restaurant.bean;

import by.itacademy.restaurant.configuration.VacationType;

import java.util.Calendar;

public class Vacation {

    private int id;
    private VacationType vacationType;
    private Calendar beginDate;
    private Calendar endDate;

    public Vacation(int id, VacationType vacationType, Calendar beginDate, Calendar endDate) {
        this.id = id;
        this.vacationType = vacationType;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VacationType getVacationType() {
        return vacationType;
    }

    public void setVacationType(VacationType vacationType) {
        this.vacationType = vacationType;
    }

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacation vacation = (Vacation) o;

        if (id != vacation.id) return false;
        if (vacationType != vacation.vacationType) return false;
        if (beginDate != null ? !beginDate.equals(vacation.beginDate) : vacation.beginDate != null) return false;
        return endDate != null ? endDate.equals(vacation.endDate) : vacation.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vacationType != null ? vacationType.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", vacationType=" + vacationType +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
