import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/delete")
public class ServletDelete extends HttpServlet {
    DataBaseQueries dataBaseQueries = new DataBaseQueries();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        try (Connection connection = ConnectionToDB.getDBConnection()) {
            User user = dataBaseQueries.getUserById(id);
            PreparedStatement statement = connection.prepareStatement(dataBaseQueries.getDELETE_USER_FROM_USERS());

            statement.setLong(1, id);

            if (statement.executeUpdate() == 1) {
                getServletContext().getRequestDispatcher("/pages/Successful.jsp").forward(req, resp);
            }else {
                getServletContext().getRequestDispatcher("/pages/dangers.jsp");
            }
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
