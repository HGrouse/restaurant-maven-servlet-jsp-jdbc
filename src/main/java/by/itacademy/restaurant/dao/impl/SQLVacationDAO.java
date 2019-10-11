package by.itacademy.restaurant.dao.impl;

import by.itacademy.restaurant.configuration.VacationType;
import by.itacademy.restaurant.dao.DAOException;
import by.itacademy.restaurant.dao.VacationDAO;
import by.itacademy.restaurant.bean.Vacation;
import by.itacademy.restaurant.dao.pool.ConnectionPool;


import java.sql.*;
import java.util.Calendar;
import java.util.List;

public class SQLVacationDAO extends SQLRequest implements VacationDAO {

    @Override
    public void startVacation(int id, VacationType vacationType, Calendar date) throws DAOException {

        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SQL_START_VACATION);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, vacationType.getType());
            preparedStatement.setObject(3, new java.sql.Date(date.getTime().getTime()));
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_EMPLOYEE_VACATION_CHANGE_STATUS);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DAOException("Cannot add a new Vacation.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void endVacation(int id, VacationType vacationType, Calendar date) throws DAOException {

        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SQL_END_VACATION);
            preparedStatement.setObject(1, new java.sql.Date(date.getTime().getTime()));
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, vacationType.getType());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_EMPLOYEE_VACATION_CHANGE_STATUS);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DAOException("Cannot end a Vacation.", e);
        } finally {

            pool.closeConnection(connection, preparedStatement);
        }

    }

    @Override
    public void editVacation() throws DAOException {

    }

    @Override
    public List<Vacation> getVacationsByType(VacationType vacationType) throws DAOException {
        return null;
    }

    @Override
    public List<Vacation> getVacationsByUserId(int id) throws DAOException {
        return null;
    }
}
