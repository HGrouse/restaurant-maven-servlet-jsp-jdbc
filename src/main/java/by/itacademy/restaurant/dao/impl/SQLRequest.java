package by.itacademy.restaurant.dao.impl;

public class SQLRequest {

    //Employee
    protected final static String SQL_CHECK_REGISTRATION_INFO ="SELECT login FROM employee WHERE (login = ?) or (email = ?)";

    protected final static String SQL_ADD_PERSON = "INSERT INTO employee (login, password, email, name, surname, middleName, sex, dateOfBirth, phoneNumber, role)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?)";

    protected final static String SQL_TO_HIRE = "UPDATE employee SET role = ?, passport = ?, beganWork = ?  " +
            "WHERE id = ?";

    protected final static String SQL_TO_DISMISS = "UPDATE employee SET dateOfDismissal = ?, isFired = ? " +
            "WHERE id = ?";
    protected final static String SQL_LOG_ON = "SELECT password, isFired, id, role, name FROM employee WHERE (login = ?)";
    //Vacation
    protected final static String SQL_START_VACATION = "INSERT INTO vacations (employee_id, typeOfRest_id, dateOfStart) VALUES (?,?,?)";
    protected final static String SQL_END_VACATION = "UPDATE vacations SET dateOfEnd = ? WHERE (employee_id = ?) and (typeOfRest_id = ?)";
    protected final static String SQL_EMPLOYEE_VACATION_CHANGE_STATUS = "UPDATE employee SET onVacation = ? WHERE id = ?";
    protected final static String SQL_GET_ALL_EMPLOYEES = "SELECT id, role, name, surname, middleName FROM employee " +
            "WHERE (isFired = false) LIMIT ?, ?";
    protected final static String SQL_GET_USERS_COUNT = "SELECT COUNT(*) FROM employee WHERE (isFired = false)";
    protected final static String SQL_GET_ADMIN_ACCESS_INFO = "SELECT email, name, surname, middleName, sex, dateOfBirth," +
            " phoneNumber, login, role, passport, beganWork FROM employee WHERE id = ?";
    protected final static String SQL_GET_USER_ACCESS_INFO = "SELECT email, name, surname, middleName, sex, dateOfBirth," +
            " phoneNumber FROM employee WHERE id = ?";


}
