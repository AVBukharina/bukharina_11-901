package ru.itis.servlets;

import models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qmb6y42p";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/page_registration";

    private List<Users> usersList;

    @Override
    public void init() {
        this.usersList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            //language=SQL
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from users");

            while(result.next()){
                Users users = Users.builder()
                        .email(result.getString("email"))
                        .name(result.getString("names"))
                        .surname(result.getString("surname"))
                        .number(result.getString("numbers"))
                        .build();
                usersList.add(users);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        PrintWriter writer = response.getWriter();
//
//        writer.println("<table>");
//        writer.println("    <tr>");
//        writer.println("        <th>Email</th>");
//        writer.println("        <th>Name</th>");
//        writer.println("        <th>Surname</th>");
//        writer.println("        <th>Number</th>");
//        writer.println("    </tr>");
//
//        for (int i = 0; i < usersList.size(); i++) {
//            writer.println("<tr>");
//            writer.println("    <td>" + usersList.get(i).getEmail() + "</td>");
//            writer.println("    <td>" + usersList.get(i).getName() + "</td>");
//            writer.println("    <td>" + usersList.get(i).getSurname() + "</td>");
//            writer.println("    <td>" + usersList.get(i).getNumber() + "</td>");
//            writer.println("</tr>");
//        }
//
//        writer.println("</table>");
//
//        response.setContentType("text/html");
        request.setAttribute("usersForJsp", usersList);
        request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
    }
}
