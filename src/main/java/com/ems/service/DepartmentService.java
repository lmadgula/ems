package com.ems.service;

import com.ems.dao.DbConnection;
import com.ems.model.Department;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    public List<Department> getAll() throws Exception{
        List<Department> result = new ArrayList<>();
        String query = "select department_id, departmant_nm from department";
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

    public int create(Department department) throws SQLException {
        String queryToInsert = "Insert into department (departmant_nm) values (?)";
        Connection con = DbConnection.getConn();
        PreparedStatement prepStmt = con.prepareStatement(queryToInsert);
        prepStmt.setString(1, department.getDeptName());
        prepStmt.executeUpdate();
        String queryToGetByName = "Select department_id from department where departmant_nm = ?";
        prepStmt = con.prepareStatement(queryToGetByName);
        prepStmt.setString(1, department.getDeptName());
        var rs = prepStmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("department_id");
        } else {
            return -1;
        }
    }

    public void delete(int deptId) throws SQLException {
        String query = "delete from department where department_id = ?";
        Connection con = DbConnection.getConn();
        PreparedStatement prepStmt = con.prepareStatement(query);
        prepStmt.setInt(1, deptId);
        int updateCount = prepStmt.executeUpdate();
        System.out.println("rows impacted " + updateCount);
    }

    public void update(Department department) throws SQLException{
        String query = "update department set departmant_nm = ? where department_id = ?";
        Connection con = DbConnection.getConn();
        PreparedStatement prepStmt = con.prepareStatement(query);
        prepStmt.setString(1, department.getDeptName());
        prepStmt.setInt(2, department.getDeptId());
        int updateCount = prepStmt.executeUpdate();
        System.out.println("rows impacted " + updateCount);
    }

    public Department getOne(int id) throws SQLException {
        String query = "select departmant_nm from department where department_id = ?";
        Connection con = DbConnection.getConn();
        PreparedStatement prepStmt = con.prepareStatement(query);
        prepStmt.setInt(1, id);
        ResultSet rs = prepStmt.executeQuery();
        rs.next();
        Department result = new Department(id, rs.getString("departmant_nm"));
        return result;
    }
}
