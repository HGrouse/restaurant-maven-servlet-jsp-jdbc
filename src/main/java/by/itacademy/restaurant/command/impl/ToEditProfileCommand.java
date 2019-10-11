package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.bean.user.EditAdminAccessInfo;
import by.itacademy.restaurant.bean.user.EditUserAccessInfo;
import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.JSPPath;
import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.command.ParameterName;
import by.itacademy.restaurant.command.Role;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import by.itacademy.restaurant.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ToEditProfileCommand implements Command {

    private static final Logger log = LogManager.getLogger(ToEditProfileCommand.class);
    private final static EmployeeService service = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = null;
        HttpSession session = request.getSession(false);

        if (session != null) {
            user = (User) session.getAttribute(ParameterName.SESSION_ATTRIBUTE_USER);

        }

        String goToPage = JSPPath.INDEX_PAGE;
        EditUserAccessInfo userInfo = null;
        EditAdminAccessInfo adminInfo = null;

        if (user != null) {

            // getting user id we'll edit
            String id = request.getParameter(ParameterName.REQ_PARAM_ID);

            if (id == null) {
                id = (String) session.getAttribute(ParameterName.REQ_PARAM_ID);
                session.removeAttribute(ParameterName.REQ_PARAM_ID);
            }
            if (id == null) {
                id = String.valueOf(user.getId());
            }

            try {
                if (user.getRole().equals(Role.ADMIN.toString())) {

                    adminInfo = service.getAdminAccessInfo(id);
                    request.setAttribute("info", adminInfo);
                    goToPage = JSPPath.EDIT_PROFILE_PAGE;

                } else if (String.valueOf(user.getId()).equals(id)) {

                    userInfo = service.getUserAccessInfo(id);
                    request.setAttribute("info", userInfo);
                    goToPage = JSPPath.EDIT_PROFILE_PAGE;
                }
            } catch (ServiceException e) {
                log.error(e);
                goToPage = JSPPath.REDIRECT_TO_ERROR_PAGE;
            }
        }

        request.setAttribute(ParameterName.REQ_ATTRIBUTE_COMMAND_FOR_REDIRECT, JSPPath.REDIRECT_TO_EDIT_PROFILE_PAGE+"&id=");

        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);

        if(session !=  null){
            session.removeAttribute(ParameterName.REQ_ATTRIBUTE_ANSWER_CODE);
        }



    }
}
