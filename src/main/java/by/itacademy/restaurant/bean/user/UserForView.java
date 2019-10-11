package by.itacademy.restaurant.bean.user;

import java.io.Serializable;

public class UserForView extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String surname;
    private String middleName;

    public UserForView() {
        super();
    }

    public UserForView(int id, String role, String name, String surname, String middleName) {
        super(id, role, name);
        this.surname = surname;
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserForView that = (UserForView) o;

        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        return middleName != null ? middleName.equals(that.middleName) : that.middleName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserForView{" + super.toString() +
                "surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
