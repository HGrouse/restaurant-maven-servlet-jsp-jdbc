package by.itacademy.restaurant.dao;

import by.itacademy.restaurant.bean.user.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeDAO {

    int checkRegistrationInfo(RegistrationUserInfo regInfo) throws DAOException;
    void registration(RegistrationUserInfo registrationUserInfo) throws DAOException;
    void toHire(EmploymentInfo info) throws DAOException;
    User logOn(String login, String password) throws DAOException;
    void editInfo(int userId, Set<Map.Entry<String, String>> setEnt) throws DAOException;
    List<UserForView> getAllEmployees(int firstUserToShow, int countUsersOnPage) throws DAOException;
    int getUsersCount() throws DAOException;
    void toDismiss(int id, String dateOfDismissal) throws DAOException;
    EditAdminAccessInfo getAdminAccessInfo(int id) throws DAOException;
    EditUserAccessInfo getUserAccessInfo(int id) throws DAOException;

    // NEED REALISE

    List<User> getAllEmployeesByRole();
    List<User> getAllEmployeesByRoleNameAndSurname();
    User getEmployeeInfoById(int id);
}
