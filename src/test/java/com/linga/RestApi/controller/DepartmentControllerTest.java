package com.linga.RestApi.controller;

import com.linga.RestApi.entity.Department;
import com.linga.RestApi.error.DepartmentNotFoundException;
import com.linga.RestApi.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//#To unit test the controller layer we use @WebMvcTest

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;
    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress("Drane Hall")
                .departmentCode("Univs")
                .departmentId((1L))
                .departmentName("UniversityStudies")
                .build();

    }

    @Test
    void saveDepartment() throws Exception {
        Department inputdepartment = Department.builder()
                .departmentAddress("Drane Hall")
                .departmentCode("Univs")
                .departmentName("UniversityStudies")
                .build();
        Mockito.when(departmentService.saveDepartment(inputdepartment)).thenReturn(department);
        mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"departmentName\":\"UniversityStudies\",\n" +
                "    \"departmentAddress\":\"Drane Hall\",\n" +
                "    \"departmentCode\":\"Univs\"\n" +
                "}")).andExpect(status().isOk());
    }

    @Test
    void getDepartmentById() throws Exception {
        Long departmentId = 1L;
        Mockito.when(departmentService.getDepartmentById(departmentId)).thenReturn(department);
        mockMvc.perform(get("/departments/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}