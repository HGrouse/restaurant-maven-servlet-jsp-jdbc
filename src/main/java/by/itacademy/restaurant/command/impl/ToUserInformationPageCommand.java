package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.configuration.JSPPath;
import by.itacademy.restaurant.configuration.ParameterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ToUserInformationPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String goToPage = null;
        User user = null;
        HttpSession session = request.getSession(false);

        if (session != null){
            user = (User)session.getAttribute(ParameterName.SESSION_ATTRIBUTE_USER);
        }

        if (user == null) {
            goToPage = JSPPath.INDEX_PAGE;
        } else {
            goToPage = JSPPath.USER_INFORMATION_PAGE;
        }

        request.setAttribute(ParameterName.REQ_ATTRIBUTE_COMMAND_FOR_REDIRECT, JSPPath.REDIRECT_TO_USER_INFORMATION_PAGE);

        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);
    }
}
