import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class DataBaseQueries {
    private final String SELECT_ID_FROM_USERS = "SELECT * FROM users WHERE id=?";
    private final String INSERT_USER_FROM_USERS = "INSERT INTO users (id, username, user_password, created, changed, age)" +
            " VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private final String UPDATE_USERNAME_FROM_USERS = "UPDATE users SET username=?, changed=? WHERE id=?";
    private final String DELETE_USER_FROM_USERS = "DELETE FROM users WHERE id=?";
    private User user = new User();


    public User getUserById(Long id) throws SQLException {
        User user = null;

        try (Connection connection = ConnectionToDB.getDBConnection()){

            PreparedStatement statement = connection.prepareStatement(SELECT_ID_FROM_USERS);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = setToUser(resultSet);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return user;
    }

    private User setToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setUserPassword(resultSet.getString("user_password"));
        user.setCreated(resultSet.getTimestamp("created"));
        user.setChanged(resultSet.getTimestamp("changed"));
        user.setAge(resultSet.getInt("age"));
        return user;
    }

    public void updateUser(User user) throws SQLException {
        try (Connection connection = ConnectionToDB.getDBConnection()) {

            PreparedStatement statement = connection.prepareStatement(UPDATE_USERNAME_FROM_USERS);

            statement.setString(1, user.getUsername());
            statement.setTimestamp(2, user.getChanged());
            statement.setLong(3, user.getId());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
