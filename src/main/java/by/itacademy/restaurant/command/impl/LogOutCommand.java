package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        response.sendRedirect(JSPPath.INDEX_PAGE);
    }
}
