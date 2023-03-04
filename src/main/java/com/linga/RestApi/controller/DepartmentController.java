package com.linga.RestApi.controller;

import com.linga.RestApi.entity.Department;
import com.linga.RestApi.error.DepartmentNotFoundException;
import com.linga.RestApi.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    //Here we are taking the spring boot functionality to get the object of a DepartmentService using @Autowired annotation.
    // Here the dependency injection happens
    @Autowired
    private DepartmentService departmentService;

    //this is a inbuilt logger of spring boot
    private  final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody  Department department){
        LOGGER.info("Inside save department api");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments(){
        LOGGER.info("Inside Get ALl Departments api");
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public  Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {

        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department Deleted Successfully!";
    }
    @PutMapping("/departments/{id}")
    public Department updateDepartmentById( @PathVariable("id") Long departmentId,
                                            @RequestBody Department department ){
        return  departmentService.updateDepartmentById(departmentId,department);
    }

    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String deptName){
        return departmentService.getDepartmentByName(deptName);
    }
}
