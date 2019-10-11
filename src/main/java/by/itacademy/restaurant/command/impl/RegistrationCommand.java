package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.command.*;
import by.itacademy.restaurant.bean.user.RegistrationUserInfo;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import by.itacademy.restaurant.service.ServiceProvider;
import by.itacademy.restaurant.service.validation.VerificationCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationCommand implements Command {

    private static final Logger log = LogManager.getLogger(RegistrationCommand.class);
    private final static EmployeeService service = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter(ParameterName.REQ_PARAM_LOGIN);
        String password = request.getParameter(ParameterName.REQ_PARAM_PASSWORD);
        String email = request.getParameter(ParameterName.REQ_PARAM_EMAIL);
        String name = request.getParameter(ParameterName.REQ_PARAM_NAME);
        String surname = request.getParameter(ParameterName.REQ_PARAM_SURNAME);
        String middleName = request.getParameter(ParameterName.REQ_PARAM_MIDDLE_NAME);
        String sex = request.getParameter(ParameterName.REQ_PARAM_SEX);
        String phoneNumber = request.getParameter(ParameterName.REQ_PARAM_PHONE_NUMBER);
        String dateOfBirth = request.getParameter(ParameterName.REQ_PARAM_DATE_OF_BIRTH);

        String goToPage = null;

        RegistrationUserInfo info = new RegistrationUserInfo(login, password, email, name, surname, middleName, sex, dateOfBirth, phoneNumber);
        int verificationCode = VerificationCode.CORRECT_INFORMATION;

        try {
            verificationCode = service.register(info);
            if (verificationCode == VerificationCode.CORRECT_INFORMATION) {
                Command command = CommandProvider.getInstance().getCommand(CommandName.AUTHORIZATION.toString());
                command.execute(request, response);
            } else {
                request.setAttribute(ParameterName.REQ_ATTRIBUTE_ANSWER_CODE, verificationCode);
                goToPage = JSPPath.REGISTRATION_PAGE;
            }
        } catch (ServiceException e) {
            log.error(e);
            goToPage = JSPPath.REDIRECT_TO_ERROR_PAGE;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);
    }
}
