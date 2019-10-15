package by.itacademy.restaurant.dao.impl;

import by.itacademy.restaurant.bean.user.*;
import by.itacademy.restaurant.configuration.Role;
import by.itacademy.restaurant.dao.DAOException;
import by.itacademy.restaurant.dao.EmployeeDAO;
import by.itacademy.restaurant.dao.SQLParameterName;
import by.itacademy.restaurant.dao.impl.util.DataProtector;
import by.itacademy.restaurant.dao.pool.ConnectionPool;
import by.itacademy.restaurant.configuration.VerificationCode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SQLEmployeeDAO extends SQLRequest implements EmployeeDAO {

    private final static ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int checkRegistrationInfo(RegistrationUserInfo regInfo) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_REGISTRATION_INFO);

            preparedStatement.setString(1, regInfo.getLogin());
            preparedStatement.setString(2, regInfo.getEmail());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (regInfo.getLogin().equals(resultSet.getString(1))) {
                    return VerificationCode.LOGIN_ALREADY_EXISTS;
                }
                return VerificationCode.EMAIL_ALREADY_EXISTS;
            }
            return VerificationCode.CORRECT_INFORMATION;

        } catch (SQLException e) {
            throw new DAOException();
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement, resultSet);
            }
        }
    }

    @Override
    public void registration(RegistrationUserInfo regInfo) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD_PERSON);

            String password = DataProtector.hashPassword(regInfo.getPassword());

            preparedStatement.setString(1, regInfo.getLogin());
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, regInfo.getEmail());
            preparedStatement.setString(4, regInfo.getName());
            preparedStatement.setString(5, regInfo.getSurname());
            preparedStatement.setString(6, regInfo.getMiddleName());
            preparedStatement.setString(7, regInfo.getSex());
            preparedStatement.setObject(8, new java.sql.Date(Long.valueOf(regInfo.getDateOfBirth())));
            preparedStatement.setString(9, regInfo.getPhoneNumber());
            preparedStatement.setString(10, Role.CANDIDATE.toString());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Cannot add new Person", e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement);
            }
        }
    }

    @Override
    public void toHire(EmploymentInfo info) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_TO_HIRE);

            preparedStatement.setString(1, info.getRoleType().toString());
            preparedStatement.setString(2, info.getPassport());
            preparedStatement.setObject(3, new java.sql.Date(info.getBeganWork().getTime().getTime()));
            preparedStatement.setInt(4, info.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Cannot hire a new employee", e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement);
            }
        }
    }

    @Override
    public void editInfo(int userId, Set<Map.Entry<String, String>> values) throws DAOException {

        ConnectionPool pool = null;
        Connection connection = null;
        Statement statement = null;

        StringBuilder SQL = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();

            statement = connection.createStatement();
            connection.setAutoCommit(false);


            for (Map.Entry<String, String> value : values) {

                SQL = buildSQL(value.getKey(), value.getValue(), userId);
                statement.addBatch(SQL.toString());
            }

            statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DAOException("Cannot change User Information", e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, statement);
            }
        }
    }

    private StringBuilder buildSQL (String key, String value, int userId) {

        StringBuilder SQL = new StringBuilder("UPDATE employee SET ");
        SQL.append(key);
        SQL.append(" = '");

        if (key.equals(SQLParameterName.SQL_PARAM_DATE_OF_BIRTH) ||
                key.equals(SQLParameterName.SQL_PARAM_BEGAN_WORK) ||
                key.equals(SQLParameterName.SQL_PARAM_DATE_OF_DISMISSAL)) {

            SQL.append(new java.sql.Date(Long.valueOf(value)));

        } else if (key.equals(SQLParameterName.SQL_PARAM_PASSWORD)) {
            SQL.append(DataProtector.hashPassword(value));

        } else {
            SQL.append(value);
        }
        SQL.append("' WHERE id = ");
        SQL.append(userId);
        SQL.append("; ");

        return SQL;
    }

    @Override
    public User logOn(String login, String password) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        User user = null;

        String hashedPassword = DataProtector.hashPassword(password);

        String realPassword = null;
        boolean isFired = false;
        int id = 0;
        String role = null;
        String name = null;

        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_LOG_ON);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            realPassword = resultSet.getString(1);
            isFired = resultSet.getBoolean(2);

            if (!isFired && hashedPassword.equals(realPassword)) {

                id = resultSet.getInt(3);
                role = resultSet.getString(4);
                name = resultSet.getString(5);

                user = new User(id, role, name);
                return user;
            }

        } catch (SQLException e) {
            throw new DAOException("Can't login", e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement, resultSet);
            }
        }

        return user;
    }

    @Override
    public int getUsersCount() throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int count = 0;

        try {

            connection = pool.takeConnection();

            preparedStatement = connection.prepareStatement(SQL_GET_USERS_COUNT);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }


        } catch (SQLException e) {
            throw new DAOException("Can't count users", e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement, resultSet);
            }
        }

        return count;
    }

    @Override
    public List<UserForView> getAllEmployees(int firstUserToShow, int countUsersOnPage) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<UserForView> users = new ArrayList<UserForView>();

        try {

            connection = pool.takeConnection();

            preparedStatement = connection.prepareStatement(SQL_GET_ALL_EMPLOYEES);
            preparedStatement.setInt(1, firstUserToShow);
            preparedStatement.setInt(2, countUsersOnPage);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(new UserForView(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }

        } catch (SQLException e) {
            throw new DAOException("Cannot change User Information", e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement, resultSet);
            }
        }

        return users;
    }

    @Override
    public void toDismiss(int id, String dateOfDismissal) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_TO_DISMISS);


            preparedStatement.setObject(1, new java.sql.Date(Long.valueOf(dateOfDismissal)));
            preparedStatement.setBoolean(2, true);
            preparedStatement.setInt(3, id);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Cannot hire a new employee", e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement);
            }
        }

    }

    @Override
    public EditAdminAccessInfo getAdminAccessInfo(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        EditAdminAccessInfo info = null;

        try {
            connection = pool.takeConnection();

            preparedStatement = connection.prepareStatement(SQL_GET_ADMIN_ACCESS_INFO);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                info = new EditAdminAccessInfo(
                        String.valueOf(id),
                        "",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement, resultSet);
            }
        }

        return info;
    }

    @Override
    public EditUserAccessInfo getUserAccessInfo(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        EditUserAccessInfo info = null;

        try {
            connection = pool.takeConnection();

            preparedStatement = connection.prepareStatement(SQL_GET_USER_ACCESS_INFO);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                info = new EditUserAccessInfo(
                        String.valueOf(id),
                        "",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(connection, preparedStatement, resultSet);
            }
        }

        return info;
    }

    @Override
    public List<User> getAllEmployeesByRole() {
        return null;
    }

    @Override
    public List<User> getAllEmployeesByRoleNameAndSurname() {
        return null;
    }

    @Override
    public User getEmployeeInfoById(int id) {
        return null;
    }

}


