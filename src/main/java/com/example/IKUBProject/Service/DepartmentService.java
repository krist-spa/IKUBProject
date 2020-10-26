package com.example.IKUBProject.Service;

import com.example.IKUBProject.DTO.DepartmentDTO;
import com.example.IKUBProject.Entity.Department;
import com.example.IKUBProject.Model.AjaxResponse;
import com.example.IKUBProject.Model.DataTable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    DataTable getDepartments(Integer draw, Integer start, Integer length);
    AjaxResponse createDepartment(DepartmentDTO departmentDTO);
    Department findDepartmentById(Integer id);
    AjaxResponse deleteDepartment(Integer departmentid);
    List<Department> getAllDepartments();
    AjaxResponse updateDepartment(DepartmentDTO departmentDTO);
}
