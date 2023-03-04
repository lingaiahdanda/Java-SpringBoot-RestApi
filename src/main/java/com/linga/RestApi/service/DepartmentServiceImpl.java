package com.linga.RestApi.service;

import com.linga.RestApi.entity.Department;
import com.linga.RestApi.error.DepartmentNotFoundException;
import com.linga.RestApi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {

        Optional<Department> dept= departmentRepository.findById(departmentId);
        if(!dept.isPresent()){
            throw  new DepartmentNotFoundException("Department is not available");
        }
        return dept.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {

        Department dept = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())){
                dept.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            dept.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            dept.setDepartmentCode(department.getDepartmentCode());
        }

        return  departmentRepository.save(dept);
    }

    @Override
    public Department getDepartmentByName(String deptName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(deptName);
    }


}
