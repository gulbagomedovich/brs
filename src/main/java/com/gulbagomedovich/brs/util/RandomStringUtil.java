package com.gulbagomedovich.brs.util;

public class RandomStringUtil {

    public static String getAlphanumericString(int length) {
        String alphanumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (alphanumericString.length() * Math.random());

            sb.append(alphanumericString.charAt(index));
        }

        return sb.toString();
    }

}
