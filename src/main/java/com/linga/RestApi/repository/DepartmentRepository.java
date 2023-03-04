package com.linga.RestApi.repository;

import com.linga.RestApi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/*
This is a interface which is also a repository where we can do CRUD with database.
It extends JPA repository for that, we need to provide the entity we are using and its primary key type
*/
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    public Department findByDepartmentName(String departmentName);


    public Department findByDepartmentNameIgnoreCase(String departmentName);
}
