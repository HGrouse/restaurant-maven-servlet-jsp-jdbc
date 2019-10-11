package by.itacademy.restaurant.dao;

import by.itacademy.restaurant.dao.impl.SQLEmployeeDAO;
import by.itacademy.restaurant.dao.impl.SQLVacationDAO;

public class DAOProvider {

    private final static DAOProvider instance = new DAOProvider();

    private EmployeeDAO employeeDao = new SQLEmployeeDAO();
    private VacationDAO vacationDAO = new SQLVacationDAO();

    private DAOProvider() {}

    public static DAOProvider getInstance(){
        return instance;
    }

    public EmployeeDAO getEmployeeDao() {
        return employeeDao;
    }

    public VacationDAO getVacationDAO() {
        return vacationDAO;
    }
}
