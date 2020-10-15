package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("cookieExample", UUID.randomUUID().toString());
        response.addCookie(cookie);
        request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("cookieExample")){
                System.out.println(cookie.getValue() );
            }
        }*/
        String colorValue = request.getParameter("color");
        response.addCookie(new Cookie("color", colorValue));
        response.sendRedirect("/profile");
    }
}
