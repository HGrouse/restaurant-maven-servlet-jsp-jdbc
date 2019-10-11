package by.itacademy.restaurant.dao;

import by.itacademy.restaurant.bean.user.User;

import java.util.Calendar;
import java.util.List;

public interface WorkDayDAO {

    void setWorkDay(int userId, int shift);
    void editWorkDay(int id, int userId, int shift);

    List<User> getUsersByDateAndAdress(Calendar date, String address);

}
