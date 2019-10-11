package by.itacademy.restaurant.service;

import by.itacademy.restaurant.configuration.VacationType;

import java.util.Calendar;

public interface VacationService {

    void startVacation(int id, VacationType vacationType, Calendar date);
    void endVacation(int id, VacationType vacationType, Calendar date);

}
