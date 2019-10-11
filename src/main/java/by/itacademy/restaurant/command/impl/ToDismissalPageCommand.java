package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.JSPPath;
import by.itacademy.restaurant.command.ParameterName;
import by.itacademy.restaurant.command.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ToDismissalPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = null;
        HttpSession session = request.getSession(false);

        if (session != null) {
            user = (User) session.getAttribute(ParameterName.SESSION_ATTRIBUTE_USER);
        }

        String id = null;
        String goToPage = JSPPath.INDEX_PAGE;

        if (user != null) {
            if (user.getRole().equals(Role.ADMIN.toString())) {
                goToPage = JSPPath.DISMISSAL_PAGE;

                id = request.getParameter(ParameterName.REQ_PARAM_ID);
                if (id != null){
                    session.setAttribute(ParameterName.REQ_PARAM_ID, id);
                }
            } else {
                goToPage = JSPPath.USER_INFORMATION_PAGE;
            }
        }

        request.setAttribute(ParameterName.REQ_ATTRIBUTE_COMMAND_FOR_REDIRECT, JSPPath.REDIRECT_TO_DISMISSAL_PAGE);

        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);

        if(session !=  null){
            session.removeAttribute(ParameterName.REQ_ATTRIBUTE_ANSWER_CODE);
        }
    }
}
