package by.itacademy.restaurant.bean.user;

import java.io.Serializable;

public class EditAdminAccessInfo extends EditUserAccessInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String role;
    private String passport;
    private String beganWork;

    public EditAdminAccessInfo() {}

    public EditAdminAccessInfo(String id, String password, String email, String name, String surname,
                               String middleName, String sex, String dateOfBirth, String phoneNumber,
                               String login, String role, String passport, String beganWork) {
        super(id, password, email, name, surname, middleName, sex, dateOfBirth, phoneNumber);
        this.login = login;
        this.role = role;
        this.passport = passport;
        this.beganWork = beganWork;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getBeganWork() {
        return beganWork;
    }

    public void setBeganWork(String beganWork) {
        this.beganWork = beganWork;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EditAdminAccessInfo that = (EditAdminAccessInfo) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport != null) return false;
        if (beganWork != null ? !beganWork.equals(that.beganWork) : that.beganWork != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (beganWork != null ? beganWork.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                super.toString() +
                "login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", passport='" + passport + '\'' +
                ", beganWork=" + beganWork +
                '}';
    }
}
