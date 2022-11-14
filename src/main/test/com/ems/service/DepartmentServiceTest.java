package com.ems.service;

import com.ems.model.Department;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceTest extends TestCase {

    public void testGetAll() throws Exception {
        DepartmentService testObject = new DepartmentService();
        List<Department> result = testObject.getAll();
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("HR", result.get(0).getDeptName());
    }

    public void testCreate() throws SQLException {
        DepartmentService testObject = new DepartmentService();
        Department testDept = new Department();
        testDept.setDeptName("Public ' Relations");
        testObject.create(testDept);
    }

    public void testDelete() throws SQLException {
        DepartmentService testObject = new DepartmentService();
        Department testDept = new Department();
        testDept.setDeptId(5);
        testObject.delete(testDept);
    }

    public void testUpdate() throws SQLException {
        DepartmentService testObject = new DepartmentService();
        Department testDept = new Department(10, "Human Resources");
        testObject.update(testDept);
    }

    public void testGetOne() throws SQLException {
        DepartmentService testObject = new DepartmentService();
        Department result = testObject.getOne(1);
        Assert.assertEquals("Human Resources",  result.getDeptName());
    }
}