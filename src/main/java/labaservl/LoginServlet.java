package labaservl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private final DatabaseServlet databaseServlet = new DatabaseServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Проверяем пользователя в базе данных
        boolean isAuthenticated = DatabaseServlet.isUserInDatabase(username, password);

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("Authentication result: " + isAuthenticated);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Здесь предоставим HTML-форму с кнопкой для пользователя
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Authentication Simulation</h1>");
        writer.println("<form method='post'>");
        writer.println("Username: <input type='text' name='username'><br>");
        writer.println("Password: <input type='password' name='password'><br>");
        writer.println("<input type='submit' value='Submit'>");
        writer.println("</form>");

        // Если есть параметр authenticated, то покажем результат авторизации
        String authenticatedParam = req.getParameter("authenticated");
        if (authenticatedParam != null) {
            boolean isAuthenticated = Boolean.parseBoolean(authenticatedParam);
            writer.println("<p>Authentication result: " + isAuthenticated + "</p>");
        }

        writer.println("</body></html>");
    }

}
