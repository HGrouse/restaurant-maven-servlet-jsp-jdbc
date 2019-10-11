package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.JSPPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NoSuchCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.sendRedirect(JSPPath.REDIRECT_TO_ERROR_PAGE);
    }
}
