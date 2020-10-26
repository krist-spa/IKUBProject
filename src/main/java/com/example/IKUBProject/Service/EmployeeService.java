package com.example.IKUBProject.Service;

import com.example.IKUBProject.DTO.EmployeeDTO;
import com.example.IKUBProject.Entity.Employee;
import com.example.IKUBProject.Model.AjaxResponse;
import com.example.IKUBProject.Model.DataTable;

import java.util.Optional;

public interface EmployeeService {


    DataTable getEmployees(Integer draw, Integer start, Integer length);
    AjaxResponse createEmployee(EmployeeDTO employeeDTO);
    Employee findEmployeeById(Integer employeeid);
    AjaxResponse deleteEmployee(Integer employeeid);

    AjaxResponse updateEmployee(EmployeeDTO employeeDTO);
}
