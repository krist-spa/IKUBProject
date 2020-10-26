package com.example.IKUBProject;

import com.example.IKUBProject.DTO.DepartmentDTO;
import com.example.IKUBProject.Entity.Department;
import com.example.IKUBProject.Model.AjaxResponse;
import com.example.IKUBProject.Repository.DepartmentRepository;
import com.example.IKUBProject.Service.DepartmentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentServiceTest {

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Mock
    private TestEntityManager entityManager;

    @Test
    @Rollback(false)
    @Order(1)
    public void createDepartment(){
        DepartmentDTO departmentDTO = new DepartmentDTO("test");
        AjaxResponse response = departmentService.createDepartment(departmentDTO);
        assertThat(response.getValue()).isNotNull();
    }



    @Test
    @Rollback(false)
    @Order(2)
    public void deleteDepartment(){
        Department department = new Department("test");
        this.entityManager.persist(department);
        departmentService.deleteDepartment(1);
        Department department2 = departmentService.findDepartmentById(1);
        assertThat(department2).isNull();
    }
}
