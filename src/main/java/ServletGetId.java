import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/get")
public class ServletGetId extends HttpServlet {
    DataBaseQueries dataBaseQueries = new DataBaseQueries();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        try {
            User user = dataBaseQueries.getUserById(id);

            if (user != null) {
                req.setAttribute("userId", user.getId());
                req.setAttribute("username", user.getUsername());
                req.setAttribute("userCreated", user.getCreated());
                req.setAttribute("userChange", user.getChanged());
                req.setAttribute("userAge", user.getAge());
            }

            getServletContext().getRequestDispatcher("/pages/GetId.jsp").forward(req,resp);

            ConnectionToDB.closeDBConnection();
        }catch (SQLException  e) {
            System.out.println(e);
        }
    }
}
