package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.configuration.JSPPath;
import by.itacademy.restaurant.configuration.ParameterName;
import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import by.itacademy.restaurant.service.ServiceProvider;
import by.itacademy.restaurant.configuration.VerificationCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command{

    private static final Logger log = LogManager.getLogger(AuthorizationCommand.class);
    private final static EmployeeService service = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = null;

        String login = request.getParameter(ParameterName.REQ_PARAM_LOGIN);
        String password = request.getParameter(ParameterName.REQ_PARAM_PASSWORD);

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }

        try {
            user = service.logOn(login, password);

            if (user != null) {
                session.setAttribute(ParameterName.SESSION_ATTRIBUTE_USER, user);
                response.sendRedirect(JSPPath.REDIRECT_TO_USER_INFORMATION_PAGE);
            } else {
                request.setAttribute(ParameterName.REQ_ATTRIBUTE_ANSWER_CODE, VerificationCode.INCORRECT_LOGIN);
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.INDEX_PAGE);
                dispatcher.forward(request, response);
            }

        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(JSPPath.REDIRECT_TO_ERROR_PAGE);
        }

    }

}
