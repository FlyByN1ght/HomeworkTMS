import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/save-request.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String age = req.getParameter("age");

        boolean isValidFirstName = isValidNameAndLastName(firstName);
        boolean isValidLastName = isValidNameAndLastName(lastName);
        boolean isValidAge = isValidAge(age);

        if (isValidFirstName && isValidLastName && isValidAge) {
            resp.getWriter().write("Success");
        } else {
            resp.getWriter().write("Failure");
        }
    }
    private boolean isValidNameAndLastName(String str) {
        return str != null && str.matches("[A-Z][a-z]+");
    }

    private boolean isValidAge(String str) {
        return str != null && str.matches("\\d+");
    }
}
