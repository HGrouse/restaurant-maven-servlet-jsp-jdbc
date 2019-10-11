package by.itacademy.restaurant.service.impl;

import by.itacademy.restaurant.bean.user.*;
import by.itacademy.restaurant.bean.user.EditAdminAccessInfo;
import by.itacademy.restaurant.dao.DAOException;
import by.itacademy.restaurant.dao.DAOProvider;
import by.itacademy.restaurant.dao.EmployeeDAO;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import by.itacademy.restaurant.service.impl.util.EditStringCreator;
import by.itacademy.restaurant.service.validation.DateConverter;
import by.itacademy.restaurant.service.validation.Verification;
import by.itacademy.restaurant.configuration.VerificationCode;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static by.itacademy.restaurant.configuration.VerificationCode.CORRECT_INFORMATION;


public class RestaurantEmployeeService implements EmployeeService {


    private final static EmployeeDAO employeeDAO = DAOProvider.getInstance().getEmployeeDao();

    @Override
    public User logOn(String login, String password) throws ServiceException {

        User user = null;

        try {
            if (Verification.checkAuthorizationInfo(login, password)) {
                user = employeeDAO.logOn(login, password);
            } else {
                return null;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public int register(RegistrationUserInfo info) throws ServiceException {

        //check correct data
        int returnCode = Verification.checkRegistrationInfo(info);
        if (returnCode != CORRECT_INFORMATION) {
            return returnCode;
        }

        try {
            returnCode = employeeDAO.checkRegistrationInfo(info);
            if (returnCode == CORRECT_INFORMATION) {
                employeeDAO.registration(info);
            } else {
                return returnCode;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return CORRECT_INFORMATION;
    }

    private int saveInfo(int id, Set<Map.Entry<String, String>> setEnt) throws ServiceException {

        if (setEnt.isEmpty()){
            return VerificationCode.NO_VALUES;
        }

        try {
            employeeDAO.editInfo(id, setEnt);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return CORRECT_INFORMATION;
    }

    @Override
    public int editInfo(EditUserAccessInfo info) throws ServiceException {

        Map<String, String> fieldsToChange;
        Set<Map.Entry<String, String>> setEnt;

        //check correct data
        int returnCode = Verification.checkEditInfo(info);
        if (returnCode != CORRECT_INFORMATION) {
            return returnCode;
        }

        int id = Integer.parseInt(info.getId());

        fieldsToChange = EditStringCreator.mapCreate(info);
        setEnt = fieldsToChange.entrySet();

        return saveInfo(id, setEnt);
    }

    @Override
    public int editInfo(EditAdminAccessInfo info) throws ServiceException {

        Map<String, String> fieldsToChange;
        Set<Map.Entry<String, String>> setEnt;

        //check correct data
        int returnCode = Verification.checkEditInfo(info);
        if (returnCode != CORRECT_INFORMATION) {
            return returnCode;
        }

        int id = Integer.parseInt(info.getId());

        fieldsToChange = EditStringCreator.mapCreate(info);
        setEnt = fieldsToChange.entrySet();

        return saveInfo(id, setEnt);
    }

    @Override
    public List<UserForView> getAllEmployees(int firstUserToShow, int countUsersOnPage) throws ServiceException {

        try {
            return employeeDAO.getAllEmployees(firstUserToShow, countUsersOnPage);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getUsersCount() throws ServiceException {
        try {
            return employeeDAO.getUsersCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void toHire(EmploymentInfo info) {

        try {
            employeeDAO.toHire(info);
        } catch (DAOException daoException) {
            daoException.printStackTrace();
        }

    }

    @Override
    public int toDismiss(int id, String dateOfDismissal) throws ServiceException {



        if(!dateOfDismissal.equals("")){
            String date = DateConverter.convert(dateOfDismissal);
            if (date == null) {
                return VerificationCode.INCORRECT_DATE;
            }
            dateOfDismissal = date;
        } else {
            return VerificationCode.NO_VALUES;
        }

        try {
            employeeDAO.toDismiss(id, dateOfDismissal);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return CORRECT_INFORMATION;
    }

    @Override
    public EditAdminAccessInfo getAdminAccessInfo(String id) throws ServiceException {

        try {
            return employeeDAO.getAdminAccessInfo(Integer.parseInt(id));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public EditUserAccessInfo getUserAccessInfo(String id) throws ServiceException {
        try {
            return employeeDAO.getUserAccessInfo(Integer.parseInt(id));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}


