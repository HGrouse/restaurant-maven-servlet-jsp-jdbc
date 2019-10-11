package by.itacademy.restaurant.dao;

import by.itacademy.restaurant.bean.Shift;

import java.util.Calendar;

public interface ShiftDAO {

    void addShift(Shift shift);
    void editShift(Shift shift);
    void deleteShiftById(int id);

    void getShiftByDate(Calendar date);
    void getShiftByUserId(int id);

}
