import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/create")
public class ServletCreate extends HttpServlet {
    DataBaseQueries dataBaseQueries = new DataBaseQueries();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/CreateUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));

        User user = creatUser(username, password,age);

        try (Connection connection = ConnectionToDB.getDBConnection()) {
            PreparedStatement statement = connection.prepareStatement(dataBaseQueries.getINSERT_USER_FROM_USERS());

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getUserPassword());
            statement.setTimestamp(3, user.getCreated());
            statement.setTimestamp(4, user.getChanged());
            statement.setInt(5, user.getAge());

            statement.executeUpdate();

            if (statement.executeUpdate() == 1) {
                getServletContext().getRequestDispatcher("/pages/Successful.jsp").forward(req,resp);
                ConnectionToDB.closeDBConnection();
            }else {
                getServletContext().getRequestDispatcher("/pages/dangers.jsp").forward(req, resp);
                ConnectionToDB.closeDBConnection();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private User creatUser(String username, String password, int age) {
        User user = new User();
        user.setUsername(username);
        user.setUserPassword(password);
        user.setAge(age);
        if(user.getUsername() !=null && user.getUserPassword() != null && user.getAge() != null) {
            user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            user.setChanged(null);
            return user;
        }
        return null;
    }
}
