import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/change-login")
public class ServletChangeLogin extends HttpServlet {
    DataBaseQueries dataBaseQueries = new DataBaseQueries();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       Long id =Long.valueOf(req.getParameter("id"));
       String newUsername = req.getParameter("login");

       try {
           User user = dataBaseQueries.getUserById(id);

           if (user != null) {
               user.setUsername(newUsername);
               user.setChanged(Timestamp.valueOf(LocalDateTime.now()));

               dataBaseQueries.updateUser(user);
               getServletContext().getRequestDispatcher("/pages/Successful.jsp").forward(req, resp);
               ConnectionToDB.closeDBConnection();
           }
       }catch (SQLException e) {
           System.out.println(e);
       }
    }
}
