package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;

import java.sql.SQLException;
import java.util.Map;

import static com.dal.group7.constants.ViewConstants.EXCEPTION_ENCRYPTION;

public class PwdEncrypt {
    private final Dao<String, String> pwdDao;

    public PwdEncrypt(Dao<String, String> pwdDao) {
        this.pwdDao = pwdDao;
    }

    public String getEncryptedPwd(String inputPwd) {
        String encryptedPwd ="";
        try {
            Map<String, String> hmap = pwdDao.getValue();
            encryptedPwd = getStringHash(inputPwd, hmap);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_ENCRYPTION + e);
        }
        return encryptedPwd;
    }

    public String getStringHash(String inputPwd, Map<String, String> hmap) {
        String hashPwd = "";
        String[] hashPwdArr = inputPwd.split("");
        for (int i = 0; i < inputPwd.length(); i++) {
            if(hmap.containsKey(hashPwdArr[i])) {
                hashPwd += hmap.get(hashPwdArr[i]);
            } else {
                hashPwd+= hashPwdArr[i];
            }
        }
        return hashPwd;
    }
}
