package com.dal.group7.service.implementation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PwdEncryptTest {

    @Test
    void test_getStringHashEquals() {
        final HashMap<String, String> expectedMap = new HashMap<String, String>() {
            {
                put("a", "xt");
                put("g", "xx");
                put("2", "y");
                put("@", "-");
            }
        };
        String inputPwd = "ag@2";
        PwdEncrypt pwdE = new PwdEncrypt(null);
        String hashedPwd = pwdE.getStringHash(inputPwd, expectedMap);
        assertEquals("xtxx-y",hashedPwd);
    }

    @Test
    void test_getStringHashNotEquals() {
        final HashMap<String, String> expectedMap = new HashMap<String, String>() {
            {
                put("a", "xt");
                put("g", "xx");
                put("2", "y");
                put("@", "-");
            }
        };
        String inputPwd = "ag@2";
        PwdEncrypt pwdE = new PwdEncrypt(null);
        String hashedPwd = pwdE.getStringHash(inputPwd, expectedMap);
        assertFalse(hashedPwd.equals(inputPwd));
    }
}