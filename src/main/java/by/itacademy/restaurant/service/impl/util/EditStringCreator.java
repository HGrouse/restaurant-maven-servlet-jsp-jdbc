package by.itacademy.restaurant.service.impl.util;

import by.itacademy.restaurant.bean.user.EditAdminAccessInfo;
import by.itacademy.restaurant.bean.user.EditUserAccessInfo;

import java.util.HashMap;
import java.util.Map;

public final class EditStringCreator {

    private EditStringCreator() {}

    private static void subStringCreate(Map<String, String> fieldsToChange, String subString, String text) {

        if (!"".equals(subString)) {
            fieldsToChange.put(text, subString);
        }

    }

    public static Map<String, String> mapCreate(EditUserAccessInfo info) {

        Map<String, String> fieldsToChange = new HashMap<String, String>();

        subStringCreate(fieldsToChange, info.getPassword(), "password");
        subStringCreate(fieldsToChange, info.getEmail(), "email");
        subStringCreate(fieldsToChange, info.getName(), "name");
        subStringCreate(fieldsToChange, info.getSurname(), "surname");
        subStringCreate(fieldsToChange, info.getMiddleName(), "middleName");
        subStringCreate(fieldsToChange, info.getSex(), "sex");
        subStringCreate(fieldsToChange, info.getDateOfBirth(), "dateOfBirth");
        subStringCreate(fieldsToChange, info.getPhoneNumber(), "phoneNumber");

        return fieldsToChange;
    }

    public static Map<String, String> mapCreate(EditAdminAccessInfo info) {

        Map<String, String> fieldsToChange = null;

        fieldsToChange = mapCreate((EditUserAccessInfo) info);

        subStringCreate(fieldsToChange, info.getLogin(), "login");
        subStringCreate(fieldsToChange, info.getPassport(), "passport");
        subStringCreate(fieldsToChange, info.getBeganWork(), "beganWork");
        subStringCreate(fieldsToChange, info.getRole(), "role");

        return fieldsToChange;
    }
}
