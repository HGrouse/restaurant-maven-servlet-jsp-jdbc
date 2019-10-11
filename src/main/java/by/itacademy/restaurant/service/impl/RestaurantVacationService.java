package by.itacademy.restaurant.service.impl;

import by.itacademy.restaurant.dao.DAOException;
import by.itacademy.restaurant.dao.DAOProvider;
import by.itacademy.restaurant.dao.VacationDAO;
import by.itacademy.restaurant.command.VacationType;
import by.itacademy.restaurant.service.VacationService;

import java.util.Calendar;

public class RestaurantVacationService implements VacationService {

    @Override
    public void startVacation(int id, VacationType vacationType, Calendar date) {

        DAOProvider provider = DAOProvider.getInstance();
        VacationDAO vacationDAO = provider.getVacationDAO();

        try {
            vacationDAO.startVacation(id, vacationType, date);
        } catch (DAOException daoException) {
            //Service Exception
        }
    }

    @Override
    public void endVacation(int id, VacationType vacationType, Calendar date) {

//        DAOProvider provider = DAOProvider.getInstance();
//        VacationDAO vacationDAO = provider.getVacationDAO();
//
//        java.sql.Date SQLDateFormat = new java.sql.Date(dateOfEnd.getTime());
//        int typeOfRest_id = vacation.getType();
//
//        vacationDAO.endVacation(employee_id, typeOfRest_id, SQLDateFormat);
    }
}
