package com.linga.RestApi.service;

import com.linga.RestApi.entity.Department;
import com.linga.RestApi.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    public Department saveDepartment(Department department);
    public List<Department> getAllDepartments();

    Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartmentById(Long departmentId, Department department);

    Department getDepartmentByName(String deptName);
}
