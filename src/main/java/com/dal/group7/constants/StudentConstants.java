package com.dal.group7.constants;

import java.util.ArrayList;
import java.util.List;

public class StudentConstants {

    private static final List<String> invalidDomains = new ArrayList<>();

    private static final String EMAIL_DELIMITER = "@";

    public static List<String> getInvalidDomains() {
        invalidDomains.add("gmail.com");
        invalidDomains.add("yahoo.com");
        invalidDomains.add("outlook.com");
        invalidDomains.add("facebook.com");
        return invalidDomains;
    }

    public static String getEmailDelimiter() {
        return EMAIL_DELIMITER;
    }

}
