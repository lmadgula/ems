package com.ems.controller;

import com.ems.dao.DbConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try{
            String query
                    = "select * from department"; // query to be run
            Connection con = DbConnection.getConn();

            System.out.println(
                    "Connection Established successfully");
            Statement st = con.createStatement();
            ResultSet rs
                    = st.executeQuery(query); // Execute query
            rs.next();
            String name = rs.getString("departmant_nm"); // Retrieve name from db

            request.setAttribute("name", name);



            //System.out.println(name); // Print result on console
            st.close(); // close statement
            con.close(); // close connection
            System.out.println("Connection Closed....");
        } catch(Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/department.jsp");
        dispatcher.forward(request, response);
        }

    }

