package by.itacademy.restaurant.service.validation;

import java.util.Calendar;
import java.util.TimeZone;

public final class DateConverter {

    private DateConverter() {
    }


    public static String convert(String dateOfBirth) {

        Calendar calendar = Calendar.getInstance();
        long date = 0L;

        try {
            int year = Integer.valueOf(dateOfBirth.split("\\-")[0]);
            int month = Integer.valueOf(dateOfBirth.split("\\-")[1]);
            int day = Integer.valueOf(dateOfBirth.split("\\-")[2]);

            if (day < 1 || day > 31 ||
                    month < 0 || month > 11 ||
                    year < 1950 || year > calendar.get(Calendar.YEAR) ||
                    (year >= calendar.get(Calendar.YEAR) && month > calendar.get(Calendar.MONTH)+1) ||
                    (year >= calendar.get(Calendar.YEAR) && month >= calendar.get(Calendar.MONTH)+1 && day > calendar.get(Calendar.DAY_OF_MONTH))) {
                return null;
            }


            calendar.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
            calendar.setTimeInMillis(0);
            calendar.set(year, month - 1, day);
            if (calendar.get(Calendar.MONTH) != month - 1) {
                return null;
            }

            date = calendar.getTimeInMillis();

        } catch (NumberFormatException e) {
            return null;
        }
        return String.valueOf(date);
    }
}
