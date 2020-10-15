package ru.itis.servlets;

import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qmb6y42p";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/page";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
            String email_input = request.getParameter("email_input");
            String password_input = request.getParameter("password_input");
            boolean req = usersRepositoryJdbc.pageWithCookie(email_input, password_input);
            if (req){
                String UUIDstring =  UUID.randomUUID().toString();
                Cookie cookie = new Cookie("cookieExample", UUIDstring);
                response.addCookie(cookie);
                System.out.println(UUIDstring);
                //language=SQL
                String sql = "update client set cookie = ? where email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cookie.getValue());
                preparedStatement.setString(2, email_input);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        doGet(request,response);
    }
}
