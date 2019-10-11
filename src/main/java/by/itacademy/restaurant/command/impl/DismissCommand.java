package by.itacademy.restaurant.command.impl;

import by.itacademy.restaurant.bean.user.User;
import by.itacademy.restaurant.command.Command;
import by.itacademy.restaurant.command.JSPPath;
import by.itacademy.restaurant.command.ParameterName;
import by.itacademy.restaurant.command.Role;
import by.itacademy.restaurant.service.EmployeeService;
import by.itacademy.restaurant.service.ServiceException;
import by.itacademy.restaurant.service.ServiceProvider;
import by.itacademy.restaurant.service.validation.VerificationCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DismissCommand implements Command {

    private static final Logger log = LogManager.getLogger(DismissCommand.class);
    private final static EmployeeService service = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = null;
        HttpSession session = request.getSession(false);

        if (session != null){
            user = (User)session.getAttribute(ParameterName.SESSION_ATTRIBUTE_USER);
        }

        String goToPage = JSPPath.INDEX_PAGE;
        int verificationCode = VerificationCode.CORRECT_INFORMATION;
        String idCheck = null;
        int id = 0;

        if (user != null) {
            try {
                idCheck = request.getParameter(ParameterName.REQ_PARAM_ID);
                if ("".equals(idCheck)){
                    goToPage = JSPPath.REDIRECT_TO_GET_ALL_USERS_PAGE;
                } else {
                    id = Integer.parseInt(idCheck);
                    String dateOfDismissal = request.getParameter(ParameterName.REQ_PARAM_DATE_OF_DISMISSAL);

                    if (user.getRole().equals(Role.ADMIN.toString())) {
                        verificationCode = service.toDismiss(id, dateOfDismissal);
                        if (verificationCode == VerificationCode.INCORRECT_DATE || verificationCode == VerificationCode.NO_VALUES) {

                            goToPage = JSPPath.REDIRECT_TO_DISMISSAL_PAGE;
                            session.setAttribute(ParameterName.REQ_ATTRIBUTE_ANSWER_CODE, verificationCode);
                        } else {
                            goToPage = JSPPath.REDIRECT_TO_GET_ALL_USERS_PAGE;
                        }
                    }
                }
            } catch (ServiceException e) {
                log.error(e);
                goToPage = JSPPath.REDIRECT_TO_ERROR_PAGE;
            }
        }
        response.sendRedirect(goToPage);
    }
}
