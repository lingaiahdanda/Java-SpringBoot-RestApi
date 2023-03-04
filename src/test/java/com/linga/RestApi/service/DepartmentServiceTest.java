package com.linga.RestApi.service;

import com.linga.RestApi.entity.Department;
import com.linga.RestApi.repository.DepartmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;
    @BeforeEach
//    @BeforeAll
//    @AfterEach
//    @AfterAll
    void setUp() {
//        Here Before each test we are mocking the repository layer by creating a fake object
        Department department = Department.builder()
                .departmentName("universityStudies")
                .departmentAddress("Drane hall 301")
                .departmentCode("UnivStud")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("universityStudies")).thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid Department Name")
    public void IfValidDeptName_thenDeptShouldFound(){
        String departmentName = "universityStudies";
        Department found = departmentService.getDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }



}