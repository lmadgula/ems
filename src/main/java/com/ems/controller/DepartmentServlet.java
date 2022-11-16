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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            deptService.create(toAdd);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (SQLException e) {
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

    public static Map<String, String> getParameterMap(HttpServletRequest request) {

        BufferedReader br = null;
        Map<String, String> dataMap = null;

        try {

            InputStreamReader reader = new InputStreamReader(
                    request.getInputStream());
            br = new BufferedReader(reader);

            String data = br.readLine();

            dataMap = Splitter.on('&')
                    .trimResults()
                    .withKeyValueSeparator(
                            Splitter.on('=')
                                    .limit(2)
                                    .trimResults())
                    .split(data);

            return dataMap;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return dataMap;
    }

}

