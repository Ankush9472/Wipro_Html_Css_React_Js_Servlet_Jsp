package com.ecomm.servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("admin".equals(user) && "1234".equals(pass)) {

            // redirect to product servlet
            response.sendRedirect("product");

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
            rd.forward(request, response);
        }
    }
}