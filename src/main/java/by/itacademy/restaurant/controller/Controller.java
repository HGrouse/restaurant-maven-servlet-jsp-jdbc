package by.itacademy.restaurant.controller;

import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.CommandProvider;
import by.itacademy.restaurant.command.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class Controller extends HttpServlet {

    private static final CommandProvider provider = CommandProvider.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandName;
        Command command;

        commandName = request.getParameter(ParameterName.REQ_PARAM_COMMAND_NAME);
        command = provider.getCommand(commandName);
        command.execute(request, response);

    }

}
