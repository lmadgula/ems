package com.ems.service;

import com.ems.dao.DbConnection;
import com.ems.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public List<Employee> getAll() throws Exception{
        List<Employee> result = new ArrayList<>();
        String query = "select * from employees";
        Connection con = DbConnection.getConn();
        System.out.println("Getting employees from database");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            String fname = rs.getString("firstname"); // Retrieve name from db
            String lname = rs.getString("lastname");
            String email = rs.getString("email");
            LocalDate dob = rs.getDate("dob").toLocalDate();
            int deptId = rs.getInt("department_id");
            int empId = rs.getInt("empid");
            Employee e = new Employee(empId,fname,lname,dob,email,deptId);
            result.add(e);
        }
        return result;
    }
}
