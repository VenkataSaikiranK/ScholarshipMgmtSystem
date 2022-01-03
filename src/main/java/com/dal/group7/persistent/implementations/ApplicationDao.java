package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.SQLConstants.*;

public class ApplicationDao extends Dao<String, Application> {
    private final ConnectionManager connectionManager;

    public ApplicationDao(
            ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Optional<Application> get(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     getSelectByUserIdQuery(USER_CREDENTIAL))) {

            preparedStatement.setString(ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(new Application().from(resultSet)) :
                    Optional.empty();
        }
    }

    @Override
    public List<Application> getAllByUser(String userId) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(
                             getSelectByStudentIdQuery(APPLICATION))) {

            preparedStatement.setString(ONE, userId);
            final var resultSet = preparedStatement.executeQuery();
            List<Application> applications = new ArrayList<>();
            while (resultSet.next()) {
                applications
                        .add(new Application().from(resultSet));
            }
            
            return applications;
        }
    }

    @Override
    public Boolean doesExist(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(getSelectByApplicationIdQuery())) {
            preparedStatement.setString(ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public void insertOne(Application application) throws SQLException,
            SQLIntegrityConstraintViolationException {
        PreparedStatement statement = null;

        try (Connection connection = connectionManager.getConnection()) {

            connection.setAutoCommit(false);
            statement = connection.
                    prepareStatement(getInsertNewApplication());
            int counter = 1;

            // application
            statement.setInt(counter++,
                    application.getScheme().getApplicationID());
            statement
                    .setInt(counter++, application.getScheme().getSchemeId());
            statement.setString(counter++, application.getScheme().getUserId());
            statement.setInt(counter++,
                    application.getScheme().getInstituteId());
            statement.setString(counter++, SUBMITTED);
            statement.setDouble(counter++, application.getAcademicScore());
            statement.setDouble(counter++, application.getNonAcademicScore());
            statement.setDouble(counter, application.getProfileScore());
            statement.execute();

            // student-non-academic
            counter = 1;
            statement =
                    connection.prepareStatement(getInsertStudentNonAcademic());

            statement.setString(counter++, application.getScheme().getUserId());
            statement.setInt(counter++, application.getScheme()
                    .getNationalSportsAwards());
            statement.setInt(counter++, application.getScheme()
                    .getStateSportsAwards());
            statement.setInt(counter++, application.getScheme()
                    .getDistrictSportsAwards());
            statement.setInt(counter++, application.getScheme()
                    .getNationalArtsAwards());
            statement.setInt(counter++, application.getScheme()
                    .getStateArtsAwards());
            statement.setInt(counter, application.getScheme()
                    .getDistrictArtsAwards());
            statement.execute();

            // student finance
            counter = 1;
            statement = connection.prepareStatement(getInsertStudentFinance());

            statement.setString(counter++, application.getScheme().getUserId());
            statement.setString(counter++,
                    application.getScheme().getBankAccNumber());
            statement.setString(counter++,
                    application.getScheme().getBankIFSC());
            statement.setDouble(counter++,
                    application.getScheme().getAnnualIncome());
            statement.setString(counter++,
                    application.getScheme().getBankName());
            statement.setString(counter,
                    application.getScheme().getBankHolderName());
            statement.execute();

            // student academic
            counter = 1;
            statement = connection.prepareStatement(getInsertStudentAcademic());

            statement.setString(counter++, application.getScheme().getUserId());
            statement.setInt(counter++,
                    application.getScheme().getInstituteId());
            statement.setDouble(counter++, application.getScheme().getGpaX());
            statement.setDouble(counter++, application.getScheme().getGpaXII());
            statement.setDouble(counter++,
                    application.getScheme().getGpaBachelors());
            statement.setString(counter++, application.getScheme().getBoardX());
            statement.setString(counter++,
                    application.getScheme().getBoardXII());
            statement.setInt(counter++, application.getScheme().getBacklogX());
            statement
                    .setInt(counter++, application.getScheme().getBacklogXII());
            statement.setInt(counter++,
                    application.getScheme().getBacklogBachelors());
            statement.setDate(counter++,
                    application.getScheme().getJoiningMonthBachelors());
            statement.setDate(counter,
                    application.getScheme().getGraduationMonthBachelors());
            statement.execute();

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void updateValue(String id, String field, Object value) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     setStatusForApplication(field))
        ) {
            preparedStatement.setObject(1, value);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateUserStatus(String id, String field, String value)
            throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     setStatusForUser(field))
        ) {
            preparedStatement.setObject(1, value);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void setValues(Object object) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     setAmountForApplication())
        ) {
            Application application = (Application) object;
            preparedStatement.setDouble(1, application.getTuitionAmount());
            preparedStatement.setDouble(2, application.getInsuranceAmount());
            preparedStatement.setDouble(3, application.getTravelAmount());
            preparedStatement
                    .setDouble(4, application.getLivingExpensesAmount());
            preparedStatement.setString(5, application.getApplicationId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Optional<Application> findById(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     getSelectByApplicationIdQuery())) {

            preparedStatement.setInt(ONE, Integer.parseInt(id));
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(new Application().from(resultSet)) :
                    Optional.empty();
        }
    }

    @Override
    public List<Application> getAllApplicationByStatus() throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(
                             getSelectByInstituteApplicationStatus())) {
            preparedStatement.setString(ONE, APPROVED);
            final var resultSet = preparedStatement.executeQuery();
            List<Application> applications = new ArrayList<>();
            while (resultSet.next()) {
                applications.add(new Application().from(resultSet));
            }
            return applications;
        }
    }

    @Override
    public List<Application> getAllApplicationStatusByInstitute(int id)
            throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(
                             getSelectApprovedApplicationsByInstitute())) {
            preparedStatement.setInt(ONE, id);
            preparedStatement.setString(Integer.parseInt(TWO), APPROVED);
            final var resultSet = preparedStatement.executeQuery();
            List<Application> applications = new ArrayList<>();
            while (resultSet.next()) {
                applications.add(new Application().from(resultSet));
            }
            return applications;
        }
    }

    @Override
    public List<Application> getAll() throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(getSelectAllQuery(APPLICATION))) {
            final var resultSet = preparedStatement.executeQuery();
            List<Application> applications = new ArrayList<>();
            while (resultSet.next()) {
                applications
                        .add(new Application().from(resultSet));
            }
            return applications;
        }
    }
}
