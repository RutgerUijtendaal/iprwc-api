package com.rutgeruijtendaal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationService {

    /**
     * Check if a String is not empty
     *
     * @param string String to validate
     * @return true if string is not empty, false otherwise
     */
    public static boolean isValidString(String string) {
        return isNotEmpty(string);
    }

    /**
     * Check if a password is valid by checking length. Min length set to 3 chars.
     *
     * @param password Password to validate
     * @return true if pass is of min length, false otherwise
     */
    public static boolean isValidPassword(String password) {
        return password.length() > 7;
    }


    /**
     * Check if a name is not empty and only contains letters.
     *
     * @param name Name to validate.
     * @return true if name is valid, false otherwise.
     */
    public static boolean isValidName(String name) {
        String nPattern = "^[a-zA-Z ]*$";
        Pattern p = Pattern.compile(nPattern);
        Matcher m = p.matcher(name);
        return isNotEmpty(name) && m.matches();
    }

    public static boolean isValidPostLetters(String postLetters) {
        String nPattern = "^[a-zA-Z]*$";
        Pattern p = Pattern.compile(nPattern);
        Matcher m = p.matcher(postLetters);
        return isNotEmpty(postLetters) && m.matches() && postLetters.length() == 2;
    }

    /**
     * Check if a post number is not empty, 4 numbers and only numbers.
     *
     * @param postNumbers post numbers to check
     * @return true if week nr is valid, false otherwise.
     */
    public static boolean isValidPostNumbers(String postNumbers) {
        String nPattern = "^[0-9]*$";
        Pattern p = Pattern.compile(nPattern);
        Matcher m = p.matcher(postNumbers);
        return isNotEmpty(postNumbers) && m.matches() && postNumbers.length() == 4;
    }

    public static boolean isValidHouseNumber(int houseNumber) {
        return houseNumber > 0 && houseNumber < 1000 ;
    }

    /**
     * Check if an email is in a valid format
     *
     * @param email email to validate.
     * @return True if email is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        // Regex pattern from: http://emailregex.com
        String ePattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * Check if a String is not empty.
     *
     * @param s String to check.
     * @return true if s is not empty.
     */
    private static boolean isNotEmpty(String s) {
        return !s.trim().isEmpty();
    }
}
