package com.ems.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ems.dao.DbConnection;

@WebServlet(urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String query
                    = "select * from employees"; // query to be run
            Connection con = DbConnection.getConn();

            System.out.println(
                    "Connection Established successfully");
            Statement st = con.createStatement();
            ResultSet rs
                    = st.executeQuery(query); // Execute query
            rs.next();
            String fname = rs.getString("firstname"); // Retrieve name from db
            String lname = rs.getString("lastname");
            String email = rs.getString("email");
            request.setAttribute("fname", fname);
            request.setAttribute("lname", lname);
            request.setAttribute("email", email);


            //System.out.println(name); // Print result on console
            st.close(); // close statement
            con.close(); // close connection
            System.out.println("Connection Closed....");
        } catch(Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/employees.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        processRequest(request, response);
    }
}