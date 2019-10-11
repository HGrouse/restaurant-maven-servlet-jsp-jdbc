package by.itacademy.restaurant.bean.user;

import by.itacademy.restaurant.command.Role;

import java.util.Calendar;

public class EmploymentInfo {

    private int id;
    private Role role;
    private String passport;
    private Calendar beganWork;

    public EmploymentInfo(int id, Role role, String passport, Calendar beganWork) {
        this.id = id;
        this.role = role;
        this.passport = passport;
        this.beganWork = beganWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRoleType() {
        return role;
    }

    public void setRoleType(Role roleType) {
        this.role = roleType;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Calendar getBeganWork() {
        return beganWork;
    }

    public void setBeganWork(Calendar beganWork) {
        this.beganWork = beganWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmploymentInfo that = (EmploymentInfo) o;

        if (id != that.id) return false;
        if (role != that.role) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport != null) return false;
        return beganWork != null ? beganWork.equals(that.beganWork) : that.beganWork == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (beganWork != null ? beganWork.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", roleType=" + role +
                ", passport='" + passport + '\'' +
                ", beganWork=" + beganWork +
                '}';
    }
}