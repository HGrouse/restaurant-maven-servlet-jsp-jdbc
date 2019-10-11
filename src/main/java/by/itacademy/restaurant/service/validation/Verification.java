package by.itacademy.restaurant.service.validation;

import by.itacademy.restaurant.bean.user.EditAdminAccessInfo;
import by.itacademy.restaurant.bean.user.EditUserAccessInfo;
import by.itacademy.restaurant.bean.user.RegistrationUserInfo;
import by.itacademy.restaurant.configuration.VerificationCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Verification {

    private Verification() {
    }

    private static boolean checkString(String string, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private static int checkEditString(String string, String regex, int returnCode){

        if(!"".equals(string)){
            if (!checkString(string, regex)) {
                return returnCode;
            }
        }
        return VerificationCode.CORRECT_INFORMATION;
    }


    public static boolean checkAuthorizationInfo(String login, String password) {

        if (!checkString(login, VerificationRegex.LOGIN_REGEX)) {
            return false;
        }
        if (!checkString(password, VerificationRegex.PASSWORD_REGEX))
            return false;

        return true;
    }

    public static int checkRegistrationInfo(RegistrationUserInfo info) {

        if (!checkString(info.getLogin(), VerificationRegex.LOGIN_REGEX)) {
            return VerificationCode.INCORRECT_LOGIN;
        }

        if (!checkString(info.getPassword(), VerificationRegex.PASSWORD_REGEX)) {
            return VerificationCode.INCORRECT_PASSWORD;
        }

        if (!checkString(info.getEmail(), VerificationRegex.EMAIL_REGEX)) {
            return VerificationCode.INCORRECT_EMAIL;
        }

        if (!checkString(info.getName(), VerificationRegex.NAME_REGEX)) {
            return VerificationCode.INCORRECT_NAME;
        }

        if (!checkString(info.getSurname(), VerificationRegex.NAME_REGEX)) {
            return VerificationCode.INCORRECT_SURNAME;
        }

        if (!checkString(info.getMiddleName(), VerificationRegex.MIDDLE_NAME_REGEX)) {
            return VerificationCode.INCORRECT_MIDDLE_NAME;
        } else {
            if (info.getMiddleName().equals("")) {
                info.setMiddleName("-");
            }
        }

        if (!checkString(info.getSex(), VerificationRegex.SEX_REGEX)) {
            return VerificationCode.INCORRECT_SEX;
        }

        if (!checkString(info.getPhoneNumber(), VerificationRegex.PHONE_NUMBER_REGEX)) {
            return VerificationCode.INCORRECT_PHONE_NUMBER;
        }

        if (!checkString(info.getDateOfBirth(), VerificationRegex.DATE_REGEX)) {
            return VerificationCode.INCORRECT_DATE;
        } else {
            String dateOfBirth = DateConverter.convert(info.getDateOfBirth());
            if (dateOfBirth == null) {
                return VerificationCode.INCORRECT_DATE;
            }
            info.setDateOfBirth(dateOfBirth);
        }

        return VerificationCode.CORRECT_INFORMATION;
    }

    public static int checkEditInfo(EditUserAccessInfo info) {

        int code = 0;

        code = checkEditString(info.getId(), VerificationRegex.ID_REGEX, VerificationCode.INCORRECT_ID);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getPassword(), VerificationRegex.PASSWORD_REGEX, VerificationCode.INCORRECT_PASSWORD);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getEmail(), VerificationRegex.EMAIL_REGEX, VerificationCode.INCORRECT_EMAIL);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getName(), VerificationRegex.NAME_REGEX, VerificationCode.INCORRECT_NAME);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getSurname(), VerificationRegex.NAME_REGEX, VerificationCode.INCORRECT_SURNAME);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getMiddleName(), VerificationRegex.MIDDLE_NAME_REGEX, VerificationCode.INCORRECT_MIDDLE_NAME);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getSex(), VerificationRegex.SEX_REGEX, VerificationCode.INCORRECT_SEX);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getPhoneNumber(), VerificationRegex.PHONE_NUMBER_REGEX, VerificationCode.INCORRECT_PHONE_NUMBER);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        code = checkEditString(info.getDateOfBirth(), VerificationRegex.DATE_REGEX, VerificationCode.INCORRECT_DATE);
        if(code != VerificationCode.CORRECT_INFORMATION){
            return code;
        }

        if(!info.getDateOfBirth().equals("")){
            String dateOfBirth = DateConverter.convert(info.getDateOfBirth());
            if (dateOfBirth == null) {
                return VerificationCode.INCORRECT_DATE;
            }
            info.setDateOfBirth(dateOfBirth);
        }
        return code;
    }

    public static int checkEditInfo(EditAdminAccessInfo info) {

        int returnCode = 0;
        returnCode = checkEditInfo((EditUserAccessInfo) info);
        if (returnCode == VerificationCode.CORRECT_INFORMATION) {

            returnCode = checkEditString(info.getLogin(), VerificationRegex.LOGIN_REGEX, VerificationCode.INCORRECT_LOGIN);
            if(returnCode != VerificationCode.CORRECT_INFORMATION){
                return returnCode;
            }

            returnCode = checkEditString(info.getPassport(), VerificationRegex.PASSPORT_REGEX, VerificationCode.INCORRECT_PASSPORT);
            if(returnCode != VerificationCode.CORRECT_INFORMATION){
                return returnCode;
            }

            returnCode = checkEditString(info.getRole(), VerificationRegex.ROLE_REGEX, VerificationCode.INCORRECT_ROLE);
            if(returnCode != VerificationCode.CORRECT_INFORMATION){
                return returnCode;
            }

            returnCode = checkEditString(info.getBeganWork(), VerificationRegex.DATE_REGEX, VerificationCode.INCORRECT_DATE);
            if(returnCode != VerificationCode.CORRECT_INFORMATION){
                return returnCode;
            }

            if(!info.getBeganWork().equals("")){
                String getBeganWork = DateConverter.convert(info.getBeganWork());
                if (getBeganWork == null) {
                    return VerificationCode.INCORRECT_DATE;
                }
                info.setBeganWork(getBeganWork);
            }

        } else {
            return returnCode;
        }

        return returnCode;
    }

    public static int checkDismissalInfo(String dateOfDismissal){

        if(!dateOfDismissal.equals("")){
            String date = DateConverter.convert(dateOfDismissal);
            if (date == null) {
                return VerificationCode.INCORRECT_DATE;
            }
            dateOfDismissal = date;
        }

        return VerificationCode.CORRECT_INFORMATION;
    }
}

