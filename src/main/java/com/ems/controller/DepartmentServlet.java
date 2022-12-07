package com.ems.controller;

import com.ems.model.Department;
import com.ems.service.DepartmentService;
import com.google.common.base.Splitter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.ems.controller.ServletUtils.getParameterMap;

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
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try {
            Department toAdd = new Department();
            toAdd.setDeptName(request.getParameter("deptName"));
            int id = deptService.create(toAdd);

//            String json = new JsonObject().add("id", String.valueOf(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "DB error occured while creating new Department");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> paramMap = getParameterMap(req);
        try {
            int deptId = Integer.parseInt(paramMap.get("deptId"));
            deptService.delete(deptId);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "DB error occured while deleting department");
        }
    }

}

