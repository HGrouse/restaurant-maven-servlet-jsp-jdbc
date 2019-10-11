package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.JSPPath;
import by.itacademy.restaurant.command.ParameterName;
import by.itacademy.restaurant.service.validation.VerificationCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute(ParameterName.REQ_ATTRIBUTE_ANSWER_CODE, VerificationCode.CORRECT_INFORMATION);
        request.setAttribute(ParameterName.REQ_ATTRIBUTE_COMMAND_FOR_REDIRECT, JSPPath.REDIRECT_TO_REGISTRATION_PAGE);

        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.REGISTRATION_PAGE);
        dispatcher.forward(request, response);
    }
}
