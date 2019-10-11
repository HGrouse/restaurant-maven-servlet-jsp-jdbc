package by.itacademy.restaurant.dao;

import by.itacademy.restaurant.command.VacationType;
import by.itacademy.restaurant.bean.Vacation;

import java.util.Calendar;
import java.util.List;

public interface VacationDAO {

    void startVacation(int id, VacationType vacationType, Calendar date) throws DAOException;
    void endVacation(int id, VacationType vacationType, Calendar date) throws DAOException;

    //realise
    List<Vacation> getVacationsByUserId(int id) throws DAOException;

    //think about it
    void editVacation() throws DAOException;
    List<Vacation> getVacationsByType(VacationType vacationType) throws DAOException;


}
