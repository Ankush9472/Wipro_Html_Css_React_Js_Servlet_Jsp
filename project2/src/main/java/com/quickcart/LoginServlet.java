package com.quickcart;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.equals("ankush@gmail.com") && password.equals("54321")) {

            request.setAttribute("msg", "Logged in successfully!");

            // Go to ProductServlet
            RequestDispatcher rd = request.getRequestDispatcher("ProductServlet");
            rd.forward(request, response);

        } else {

            request.setAttribute("error", "Incorrect Email or Password!");

            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }
}