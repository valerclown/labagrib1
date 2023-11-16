package labaservl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/add-data"})
public class DatabaseServlet extends HttpServlet {
    private static final Map<String, String> database = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null) {
            // Add user data to the shared database map
            database.put(username, password);
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("Data added to the database.");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    public static boolean isUserInDatabase(String username, String password) {
        String storedPassword = database.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Add Data to Database</h1>");
        writer.println("<form method='post'>");
        writer.println("Username: <input type='text' name='username'><br>");
        writer.println("Password: <input type='password' name='password'><br>");
        writer.println("<input type='submit' value='Add Data'>");
        writer.println("</form>");
        writer.println("</body></html>");
    }
}
