package by.itacademy.restaurant.service;

import by.itacademy.restaurant.bean.user.*;
import by.itacademy.restaurant.bean.user.EditAdminAccessInfo;

import java.util.List;

public interface EmployeeService {

    int register(RegistrationUserInfo registrationUserInfo) throws ServiceException;
    User logOn(String login, String password) throws ServiceException;
    int editInfo(EditUserAccessInfo info) throws ServiceException;
    int editInfo(EditAdminAccessInfo info) throws ServiceException;
    List<UserForView> getAllEmployees(int firstUserToShow, int countUsersOnPage) throws ServiceException;
    int getUsersCount() throws ServiceException;
    int toDismiss(int id, String Date) throws ServiceException;
    EditAdminAccessInfo getAdminAccessInfo(String id) throws ServiceException;
    EditUserAccessInfo getUserAccessInfo(String id) throws ServiceException;


    void toHire(EmploymentInfo info);

}
