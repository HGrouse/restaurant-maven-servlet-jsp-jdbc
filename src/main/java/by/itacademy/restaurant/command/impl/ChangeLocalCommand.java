package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.JSPPath;
import by.itacademy.restaurant.command.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocalCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        String local = null;
        String goToPage = null;

        local = request.getParameter("local");
        session.setAttribute("local", local);

        goToPage = request.getParameter(ParameterName.REQ_ATTRIBUTE_COMMAND_FOR_REDIRECT);
        if ("".equals(goToPage)){
            goToPage = JSPPath.INDEX_PAGE;
        }
        response.sendRedirect(goToPage);
    }
}
