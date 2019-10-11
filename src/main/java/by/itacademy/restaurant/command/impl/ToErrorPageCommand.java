package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.configuration.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToErrorPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.sendRedirect(JSPPath.ERROR_PAGE);
    }
}
