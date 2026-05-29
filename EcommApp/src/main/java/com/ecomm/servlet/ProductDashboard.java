package com.ecomm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/product")
public class ProductDashboard extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        ServletConfig cg = getServletConfig();
        String value = cg.getInitParameter("Products");

        ServletContext sc = getServletContext();
        String cname = sc.getInitParameter("company_name");

        pw.println("<h2>Dashboard Page</h2>");
        pw.println("<p>Total Product Count: " + value + "</p>");
        pw.println("<p>Company Name: " + cname + "</p>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}