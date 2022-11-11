package com.ems.service;

import com.ems.dao.DbConnection;
import com.ems.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    public List<Department> getAll() throws Exception{
        List<Department> result = new ArrayList<>();
        String query = "select * from department";
        Connection con = DbConnection.getConn();
        System.out.println("Getting department from database");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            int deptId = rs.getInt("department_id");
            String deptName = rs.getString("departmant_nm");
            Department e = new Department(deptId,deptName);
            result.add(e);
        }
        return result;
    }
}
