package by.itacademy.restaurant.service.validation;

public final class VerificationRegex {

    private VerificationRegex() {
    }

    final static String LOGIN_REGEX = "^[a-zA-Z]{1}[a-zA-Z\\d]{2,15}$";
    final static String PASSWORD_REGEX = "^.{4,16}$";
    final static String NAME_REGEX = "^[a-zA-Zа-яА-Я]{1,40}$";
    final static String MIDDLE_NAME_REGEX = "^([a-zA-Zа-яА-Я]{1,40})|\\-$";
    final static String EMAIL_REGEX = "^[a-zA-Z]{1}([a-zA-Z]*[\\.\\-]?[a-zA-Z]+)*@[a-zA-Z]+\\.[a-z]{2,4}$";
    final static String SEX_REGEX = "^male|female$";
    final static String DATE_REGEX = "^[\\d]{4}\\-[\\d]{1,2}\\-[\\d]{1,2}$";
    final static String PHONE_NUMBER_REGEX = "^[\\d]{9}$";
    final static String ID_REGEX = "^[\\d]{1,10}$";
    final static String PASSPORT_REGEX = "^[a-zA-Z\\d]+$";
    final static String ROLE_REGEX = "^ADMIN|WAITER|CANDIDATE$";


}
