package com.example.IKUBProject.Controller;

import com.example.IKUBProject.DTO.DepartmentDTO;
import com.example.IKUBProject.DTO.EmployeeDTO;
import com.example.IKUBProject.Model.AjaxResponse;
import com.example.IKUBProject.Service.DepartmentService;
import com.example.IKUBProject.Service.EmployeeService;
import com.example.IKUBProject.Model.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class HomeRestController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;


    @GetMapping("/employees")
    public DataTable getEmployees(@RequestParam Integer draw, @RequestParam Integer start, @RequestParam Integer length) {
        return employeeService.getEmployees(draw, start, length);
    }

    @GetMapping("/departments")
    public DataTable getDepartments(@RequestParam Integer draw, @RequestParam Integer start, @RequestParam Integer length) {
        return departmentService.getDepartments(draw, start, length);
    }

    @PostMapping("/createEmployee")
    public AjaxResponse createEmployee(EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }


    @PostMapping("/createDepartment")
    public AjaxResponse createDepartment(DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }


    @DeleteMapping("/deleteEmployee/{employeeid}")
    public AjaxResponse deleteEmployee(@PathVariable Integer employeeid) {
        return employeeService.deleteEmployee(employeeid);
    }


    @DeleteMapping("/deleteDepartment/{departmentid}")
    public AjaxResponse deleteDepartment(@PathVariable Integer departmentid) {
        return departmentService.deleteDepartment(departmentid);
    }


    @PostMapping("/updateEmployee")
    public AjaxResponse updateEmployee(EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }


    @PostMapping("/updateDepartment")
    public AjaxResponse updateDepartment(DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(departmentDTO);
    }


}
