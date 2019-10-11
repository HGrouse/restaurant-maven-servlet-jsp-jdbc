package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.configuration.ParameterName;
import by.itacademy.restaurant.configuration.Role;
import by.itacademy.restaurant.bean.user.UserForView;
import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.configuration.JSPPath;
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
import java.util.List;

import static by.itacademy.restaurant.configuration.DefaultSetting.COUNT_USERS_ON_PAGE;
import static by.itacademy.restaurant.configuration.DefaultSetting.DEFAULT_FIRST_PAGE;


public class GetAllUsersCommand implements Command {

    private static final Logger log = LogManager.getLogger(GetAllUsersCommand.class);
    private final static EmployeeService service = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String goToPage = JSPPath.INDEX_PAGE;
        List<UserForView> users;
        User user = null;
        int countOfUsers = 0;
        double countOfPages = 0.0;
        String chosenPage = null;
        String commandForRedirect = null;

        HttpSession session = request.getSession(false);

        if (session != null){
            user = (User)session.getAttribute(ParameterName.SESSION_ATTRIBUTE_USER);
            session.removeAttribute(ParameterName.REQ_PARAM_ID);
        }

        if (user != null) {
            if (user.getRole().equals(Role.ADMIN.toString())) {

                try {
                    chosenPage = request.getParameter(ParameterName.REQ_PARAM_PAGE_NUMBER);

                    users = getUsersByPageNumber(chosenPage);

                    countOfUsers = service.getUsersCount();
                    countOfPages = Math.ceil(countOfUsers / COUNT_USERS_ON_PAGE);

                    request.setAttribute(ParameterName.REQ_ATTRIBUTE_USERS, users);
                    request.setAttribute(ParameterName.REQ_ATTRIBUTE_COUNT_OF_PAGE, countOfPages);
                    request.setAttribute(ParameterName.REQ_PARAM_PAGE_NUMBER, chosenPage);
                    request.setAttribute(ParameterName.REQ_ATTRIBUTE_COUNT_USERS_ON_PAGE, COUNT_USERS_ON_PAGE);

                    goToPage = JSPPath.GET_ALL_USERS_PAGE;
                } catch (ServiceException e) {
                    goToPage = JSPPath.REDIRECT_TO_ERROR_PAGE;
                    log.error(e);
                } catch (NumberFormatException e) {
                    goToPage = JSPPath.REDIRECT_TO_GET_ALL_USERS_PAGE;
                }
            } else {
                goToPage = JSPPath.USER_INFORMATION_PAGE;
            }
        }

        if (chosenPage != null){
            commandForRedirect = JSPPath.REDIRECT_TO_GET_ALL_USERS_PAGE+chosenPage;
        } else {
            commandForRedirect = JSPPath.REDIRECT_TO_GET_ALL_USERS_PAGE;
        }
        request.setAttribute(ParameterName.REQ_ATTRIBUTE_COMMAND_FOR_REDIRECT, commandForRedirect);

        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);
    }

    private List<UserForView> getUsersByPageNumber (String chosenPage) throws ServiceException, NumberFormatException{

        if (chosenPage == null || "".equals(chosenPage)) {
            chosenPage = DEFAULT_FIRST_PAGE;
        }
        int pageNumber = Integer.parseInt(chosenPage);
        int firstUserToShow = (COUNT_USERS_ON_PAGE * pageNumber);

        return service.getAllEmployees(firstUserToShow, COUNT_USERS_ON_PAGE);
    }
}
