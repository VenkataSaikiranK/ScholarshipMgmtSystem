package com.dal.group7.persistent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserCredential {

    private String userId;
    private String password;
    private String isHardBlock;
    private String isSoftBlock;
    private String lastLogin;
    private String securityId;
    private String securityAnswerOne;
    private String securityAnswerTwo;
    private String securityAnswerThree;
    private String failedLoginCount;
    private String roleType;
    private String isBlackListed;

    public UserCredential() {
    }

    public UserCredential(String userId, String password, String isHardBlock, String isSoftBlock, String lastLogin, String securityId, String securityAnswerOne, String securityAnswerTwo, String securityAnswerThree, String failedLoginCount, String roleType, String isBlackListed) {
        this.userId = userId;
        this.password = password;
        this.isHardBlock = isHardBlock;
        this.isSoftBlock = isSoftBlock;
        this.lastLogin = lastLogin;
        this.securityId = securityId;
        this.securityAnswerOne = securityAnswerOne;
        this.securityAnswerTwo = securityAnswerTwo;
        this.securityAnswerThree = securityAnswerThree;
        this.failedLoginCount = failedLoginCount;
        this.roleType = roleType;
        this.isBlackListed = isBlackListed;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getIsHardBlock() {
        return isHardBlock;
    }

    public String getIsSoftBlock() {
        return isSoftBlock;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getSecurityId() {
        return securityId;
    }

    public String getSecurityAnswerOne() {
        return securityAnswerOne;
    }

    public String getSecurityAnswerTwo() {
        return securityAnswerTwo;
    }

    public String getSecurityAnswerThree() {
        return securityAnswerThree;
    }

    public String getFailedLoginCount() {
        return failedLoginCount;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getIsBlackListed() {
        return isBlackListed;
    }

    public UserCredential from(ResultSet resultSet) throws SQLException {
        this.userId = resultSet.getString("user_id");
        this.password = resultSet.getString("password");
        this.isHardBlock = resultSet.getString("is_hard_blocked");
        this.isSoftBlock = resultSet.getString("is_soft_blocked");
        this.lastLogin = resultSet.getString("last_login_time");
        this.securityId = resultSet.getString("security_id");
        this.securityAnswerOne = resultSet.getString("security_answer_one");
        this.securityAnswerTwo = resultSet.getString("security_answer_two");
        this.securityAnswerThree = resultSet.getString("security_answer_three");
        this.failedLoginCount = resultSet.getString("failed_login_count");
        this.roleType = resultSet.getString("role_type");
        this.isBlackListed = resultSet.getString("is_blackListed");
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredential that = (UserCredential) o;
        return Objects.equals(userId, that.userId) && Objects.equals(password, that.password) && Objects.equals(isHardBlock, that.isHardBlock) && Objects.equals(isSoftBlock, that.isSoftBlock) && Objects.equals(lastLogin, that.lastLogin) && Objects.equals(securityId, that.securityId) && Objects.equals(securityAnswerOne, that.securityAnswerOne) && Objects.equals(securityAnswerTwo, that.securityAnswerTwo) && Objects.equals(securityAnswerThree, that.securityAnswerThree) && Objects.equals(failedLoginCount, that.failedLoginCount) && Objects.equals(roleType, that.roleType) && Objects.equals(isBlackListed, that.isBlackListed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password, isHardBlock, isSoftBlock, lastLogin, securityId, securityAnswerOne, securityAnswerTwo, securityAnswerThree, failedLoginCount, roleType, isBlackListed);
    }
}
