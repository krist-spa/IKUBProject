package com.example.IKUBProject.Service;

import com.example.IKUBProject.DTO.EmployeeDTO;
import com.example.IKUBProject.Entity.Department;
import com.example.IKUBProject.Entity.Employee;
import com.example.IKUBProject.Model.AjaxResponse;
import com.example.IKUBProject.Repository.EmployeeRepository;
import com.example.IKUBProject.Model.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentService departmentService;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    @Override
    public DataTable getEmployees(Integer draw, Integer start, Integer length) {
        DataTable dataTable = new DataTable();
        dataTable.setDraw(draw);
        long count = employeeRepository.count();
        dataTable.setRecordsTotal(count);
        dataTable.setRecordsFiltered(count);
        dataTable.setData(employeeRepository.findAll(PageRequest.of(start, length)).getContent());
        return dataTable;
    }


    @Override
    public AjaxResponse createEmployee(EmployeeDTO employeeDTO) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            if (employeeDTO.isValidEmployeeDTO()) {
                Employee employee = new Employee();
                employee.setFirstname(employeeDTO.getFirstname());
                employee.setLastname(employeeDTO.getLastname());
                Department department = departmentService.findDepartmentById(employeeDTO.getDepartmentid());
                if (department != null) {
                    employee.setDepartment(department);
                } else {
                    ajaxResponse.setError(true);
                    ajaxResponse.setMessage("Required data is missing!");
                    return ajaxResponse;
                }
                if (employeeDTO.getBirthday() != null)
                    employee.setBirthDate(employeeDTO.getBirthday());

                if (employeeDTO.getPosition() != null && !employeeDTO.getPosition().trim().isEmpty())
                    employee.setPosition(employeeDTO.getPosition());

                if (employeeDTO.getSalary() != null)
                    employee.setSalary(employeeDTO.getSalary());
                employeeRepository.save(employee);
                log.info("New Employee created: " + employee.toString());
            } else {
                ajaxResponse.setError(true);
                ajaxResponse.setMessage("Required data is missing!");
            }
        } catch (Exception ex) {
            ajaxResponse.setError(true);
            ajaxResponse.setMessage(ex.getMessage());
        }
        return ajaxResponse;
    }

    @Override
    public Employee findEmployeeById(Integer employeeid) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeid);
        return employeeOptional.orElse(null);
    }


    @Override
    public AjaxResponse deleteEmployee(Integer employeeid) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Employee employee = findEmployeeById(employeeid);
            if (employee != null) {
                employeeRepository.delete(employee);
                log.info("Employee with id: " + employeeid + " deleted successfully. ");
            } else {
                ajaxResponse.setMessage("Employee not found!");
                ajaxResponse.setError(true);
            }
        } catch (Exception ex) {
            ajaxResponse.setMessage(ex.getMessage());
            ajaxResponse.setError(true);
        }
        return ajaxResponse;
    }


    @Override
    public AjaxResponse updateEmployee(EmployeeDTO employeeDTO) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Employee employee = findEmployeeById(employeeDTO.getEmployeeid());
            if (employee != null) {
                if (employeeDTO.isValidEmployeeDTO()) {
                    employee.setFirstname(employeeDTO.getFirstname());
                    employee.setLastname(employeeDTO.getLastname());
                    Department department = departmentService.findDepartmentById(employeeDTO.getDepartmentid());
                    if (department != null) {
                        employee.setDepartment(department);
                    } else {
                        ajaxResponse.setError(true);
                        ajaxResponse.setMessage("Required data is missing!");
                        return ajaxResponse;
                    }
                    if (employeeDTO.getBirthday() != null) {
                        employee.setBirthDate(employeeDTO.getBirthday());
                    } else {
                        employee.setBirthDate(null);
                    }
                    if (employeeDTO.getPosition() != null && !employeeDTO.getPosition().trim().isEmpty()) {
                        employee.setPosition(employeeDTO.getPosition());
                    } else {
                        employee.setPosition(null);
                    }
                    if (employeeDTO.getSalary() != null) {
                        employee.setSalary(employeeDTO.getSalary());
                    } else {
                        employee.setSalary(null);
                    }
                    employeeRepository.save(employee);
                    log.info("Employee updated successfully:" +  employee.toString());
                } else {
                    ajaxResponse.setError(true);
                    ajaxResponse.setMessage("Required data is missing!");
                }
            } else {
                ajaxResponse.setMessage("Employee not found!");
                ajaxResponse.setError(true);
            }
        } catch (Exception ex) {
            ajaxResponse.setMessage(ex.getMessage());
            ajaxResponse.setError(true);
        }
        return ajaxResponse;
    }

}
