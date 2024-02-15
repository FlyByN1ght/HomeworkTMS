import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    private static Connection connection;

    public static Connection getDBConnection() throws SQLException, ClassNotFoundException {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/c71_database",
                    "postgres", "root");
            return connection;
    }

    public static void closeDBConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
