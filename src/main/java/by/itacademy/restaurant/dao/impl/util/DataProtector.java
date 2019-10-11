package by.itacademy.restaurant.dao.impl.util;

public final class DataProtector {

    private DataProtector() {
    }

    public static String hashPassword(String password) {

        int temp = password.hashCode();

        long hashCode = Math.abs((((temp/3)*temp + 31) * ((temp/2)^5 + 123) * 15748275244578853L * temp + 1) % Integer.MAX_VALUE );

        return hashCode + "";
    }

}
