package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/hello")
public class SimpleHtmlServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qmb6y42p";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/html/hello.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String email = request.getParameter("email_input");
            String password = request.getParameter("password_input");
            //language=SQL
            String sqlInsertUser = "insert into client(email, password) " +
                    "values (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
