package com.quickcart;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward to dashboard page
        RequestDispatcher rd = request.getRequestDispatcher("productdashboard.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}