package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.bean.user.EditAdminAccessInfo;
import by.itacademy.restaurant.bean.user.EditUserAccessInfo;
import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.configuration.JSPPath;
import by.itacademy.restaurant.configuration.ParameterName;
import by.itacademy.restaurant.configuration.Role;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import by.itacademy.restaurant.service.ServiceProvider;
import by.itacademy.restaurant.configuration.VerificationCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class EditProfileCommand implements Command {

    private static final Logger log = LogManager.getLogger(EditProfileCommand.class);
    private final static EmployeeService service = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = null;
        HttpSession session = request.getSession(false);

        if (session != null){
            user = (User)session.getAttribute(ParameterName.SESSION_ATTRIBUTE_USER);
        }

        String goToPage = JSPPath.INDEX_PAGE;
        int verificationCode = VerificationCode.CORRECT_INFORMATION;

        if (user != null) {
            try {

                if (user.getRole().equals(Role.ADMIN.toString())) {
                    verificationCode = adminInfoCreate(request);
                } else {
                    verificationCode = userInfoCreate(request);
                }
                goToPage = JSPPath.REDIRECT_TO_EDIT_PROFILE_PAGE;

                String id = request.getParameter(ParameterName.REQ_PARAM_ID);
                session.setAttribute("id", id);

            } catch (ServiceException e) {
                log.error(e);
                goToPage = JSPPath.REDIRECT_TO_ERROR_PAGE;
            }
        }
        if (session != null){
            session.setAttribute(ParameterName.REQ_ATTRIBUTE_ANSWER_CODE, verificationCode);
        }
        response.sendRedirect(goToPage);
    }

    public static int userInfoCreate(HttpServletRequest request) throws ServiceException {

        String id = request.getParameter(ParameterName.REQ_PARAM_ID);
        String password = request.getParameter(ParameterName.REQ_PARAM_PASSWORD);
        String email = request.getParameter(ParameterName.REQ_PARAM_EMAIL);
        String name = request.getParameter(ParameterName.REQ_PARAM_NAME);
        String surname = request.getParameter(ParameterName.REQ_PARAM_SURNAME);
        String middleName = request.getParameter(ParameterName.REQ_PARAM_MIDDLE_NAME);
        String sex = request.getParameter(ParameterName.REQ_PARAM_SEX);
        String dateOfBirth = request.getParameter(ParameterName.REQ_PARAM_DATE_OF_BIRTH);
        String phoneNumber = request.getParameter(ParameterName.REQ_PARAM_PHONE_NUMBER);

        EditUserAccessInfo info = new EditUserAccessInfo(id, password, email, name, surname, middleName, sex, dateOfBirth, phoneNumber);
        return service.editInfo(info);
    }

    public static int adminInfoCreate(HttpServletRequest request) throws ServiceException {

        String id = request.getParameter(ParameterName.REQ_PARAM_ID);
        String password = request.getParameter(ParameterName.REQ_PARAM_PASSWORD);
        String email = request.getParameter(ParameterName.REQ_PARAM_EMAIL);
        String name = request.getParameter(ParameterName.REQ_PARAM_NAME);
        String surname = request.getParameter(ParameterName.REQ_PARAM_SURNAME);
        String middleName = request.getParameter(ParameterName.REQ_PARAM_MIDDLE_NAME);
        String sex = request.getParameter(ParameterName.REQ_PARAM_SEX);
        String dateOfBirth = request.getParameter(ParameterName.REQ_PARAM_DATE_OF_BIRTH);
        String phoneNumber = request.getParameter(ParameterName.REQ_PARAM_PHONE_NUMBER);

        String login = request.getParameter(ParameterName.REQ_PARAM_LOGIN);
        String role = request.getParameter(ParameterName.REQ_PARAM_ROLE);
        String passport = request.getParameter(ParameterName.REQ_PARAM_PASSPORT);
        String beganWork = request.getParameter(ParameterName.REQ_PARAM_BEGAN_WORK);
        EditAdminAccessInfo info = new EditAdminAccessInfo(id, password, email, name, surname, middleName, sex,
                dateOfBirth, phoneNumber, login, role, passport, beganWork);

        return service.editInfo(info);
    }
}
