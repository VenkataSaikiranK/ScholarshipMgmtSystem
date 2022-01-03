package com.dal.group7.constants;

import java.util.ArrayList;

public class InstituteConstants {
    private static final ArrayList<String> invalidDomains = new ArrayList<>();

    private static final String instEmailDelimiter = "@";

    public static ArrayList<String> getInvalidDomains() {
        invalidDomains.add("gmail.com");
        invalidDomains.add("yahoo.com");
        invalidDomains.add("outlook.com");
        invalidDomains.add("facebook.com");
        return invalidDomains;
    }

    public static String getInstEmailDelimiter() {
        return instEmailDelimiter;
    }


}
