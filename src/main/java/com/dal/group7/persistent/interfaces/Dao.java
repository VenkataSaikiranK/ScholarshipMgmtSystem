package com.dal.group7.persistent.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.emptyList;

public abstract class Dao<K, T> {


    public Optional<T> get(K id) throws SQLException {
        return Optional.empty();
    }

    public List<T> getAll() throws SQLException {
        return emptyList();
    }

    public List<T> getAllApplicationByStatus() throws SQLException{
        return emptyList();
    }

    public List<T> getAllByUser(K id) throws SQLException {
        return emptyList();
    }

    public void insertOne(T t) throws SQLException {
    }

    public Map<K, T> getValue() throws SQLException {
        return EMPTY_MAP;
    }

    public Boolean doesExist(String id) throws SQLException {
        return false;
    }

    public void updateValue(K id, String field, Object value)
            throws SQLException {
    }

    public Boolean doesEmailExist(String emailId) throws SQLException {
        return false;
    }

    public void updateLastLoginTime(String emailId) throws SQLException {

    }

    public void setValues(Object object) throws SQLException {
    }

    public Optional<T> findById(K id) throws SQLException {
        return Optional.empty();
    }

    public List<T> getAllScholarshipsByCriteria(String genderCriteria, String academicCriteria, String sportsCriteria) throws SQLException{
        return emptyList();
    }

    public List<T> getAllApplicationStatusByInstitute(
            int id) throws SQLException {
        return emptyList();
    }

    public ArrayList<Integer> fetchValues() throws SQLException {
        return null;
    }

    public void incrementValue(K id, String field, double value)
            throws SQLException {
    }

    public void updateUserStatus(K id, K field, K value) throws SQLException {
    }
}
