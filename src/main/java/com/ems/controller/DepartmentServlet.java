package com.ems.controller;

import com.ems.model.Department;
import com.ems.service.DepartmentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {

    private DepartmentService deptService = new DepartmentService();

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Department> departments = deptService.getAll();
            request.setAttribute("departments", departments);
        } catch(Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/department.jsp");
        dispatcher.forward(request, response);
        }

    @Override
    protected void doPut(
        HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try {
            Department toAdd = new Department();
            toAdd.setDeptName(request.getParameter("deptName"));
            deptService.create(toAdd);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
        }
    }

}

