package com.ems.controller;

import com.ems.dao.DbConnection;
import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.service.DepartmentService;

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
import java.util.List;

@WebServlet(urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {

    DepartmentService depService = new DepartmentService();
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Department> departments = depService.getAll();
            request.setAttribute("departments", departments);
        } catch(Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/department.jsp");
        dispatcher.forward(request, response);
        }

    }

