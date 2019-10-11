package by.itacademy.restaurant.bean.user;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String role;
    private String name;

    public User () {}

    public User(int id, String role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
