package by.itacademy.restaurant.configuration;

public final class JSPPath {

    private JSPPath() {}

    public final static String INDEX_PAGE = "index.jsp";
    public final static String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";
    public final static String ERROR_PAGE = "error_page.jsp";

    public final static String USER_INFORMATION_PAGE = "/WEB-INF/jsp/user_information.jsp";
    public final static String EDIT_PROFILE_PAGE = "/WEB-INF/jsp/edit_profile.jsp";
    public final static String DISMISSAL_PAGE = "/WEB-INF/jsp/dismiss.jsp";

    //admin
    public final static String GET_ALL_USERS_PAGE = "/WEB-INF/jsp/get_all_users.jsp";

    public final static String REDIRECT_TO_REGISTRATION_PAGE  = "main?command=to_registration_page";
    public final static String REDIRECT_TO_ERROR_PAGE = "main?command=to_error_page";
    public final static String REDIRECT_TO_EDIT_PROFILE_PAGE = "main?command=to_edit_profile_page";
    public final static String REDIRECT_TO_USER_INFORMATION_PAGE ="main?command=to_user_information_page";
    public final static String REDIRECT_TO_GET_ALL_USERS_PAGE ="main?command=get_all_users&chosenPage=";
    public final static String REDIRECT_TO_DISMISSAL_PAGE = "main?command=to_dismissal_page";



}
