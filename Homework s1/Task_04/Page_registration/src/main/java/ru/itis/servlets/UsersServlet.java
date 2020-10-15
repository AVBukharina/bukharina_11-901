package ru.itis.servlets;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

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
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/page";

//    private List<User> usersList;
    private UsersRepository usersRepository;

    @Override
    public void init() {
        /*this.usersList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            //language=SQL
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from client_info");

            while (result.next()) {
                User users = User.builder()
                        .id(result.getLong("id"))
                        .firstName(result.getString("firstname"))
                        .lastName(result.getString("lastname"))
                        .age(result.getInt("age"))
                        .build();
                usersList.add(users);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
//        this.users = new ArrayList<>();
//
//        User user = User.builder()
//                .id(1L)
//                .firstName("Anastasia")
//                .lastName("Bukharina")
//                .age(19)
//                .build();
//
//        User user1 = User.builder()
//                .id(2L)
//                .firstName("Artyom")
//                .lastName("Kontratyev")
//                .age(19)
//                .build();
//
//        User user2 = User.builder()
//                .id(3L)
//                .firstName("Misha")
//                .lastName("Khovaev")
//                .age(19)
//                .build();
//
//        users.add(user);
//        users.add(user1);
//        users.add(user2);
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection    );
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //Выводим список пользователей
//        PrintWriter writer = response.getWriter();
//        writer.println("<table>");
//        writer.println("<tr>");
//        writer.println("    <th>ID</th>");
//        writer.println("    <th>First name</th>");
//        writer.println("    <th>Last name</th>");
//        writer.println("    <th>Age</th>");
//        writer.println("</tr>");
//
//        for (int i = 0; i < users.size(); i++){
//            writer.println("<tr>");
//            writer.println("    <td>" + users.get(i).getId() + "</td>");
//            writer.println("    <td>" + users.get(i).getFirstName() + "</td>");
//            writer.println("    <td>" + users.get(i).getLastName() + "</td>");
//            writer.println("    <td>" + users.get(i).getAge() + "</td>");
//            writer.println("</tr>");
//        }
//        writer.println("</table>");
//
//        response.setContentType("text/html");
        String ageValue = request.getParameter("age");
        List<User> usersList = usersRepository.findAll();
        if (ageValue != null){
            Integer age = Integer.parseInt(ageValue);
            usersList = usersRepository.findAllByAge(age);
        } else {
            usersList = usersRepository.findAll();
        }

        request.setAttribute("usersForJsp", usersList);
        request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
    }
}
