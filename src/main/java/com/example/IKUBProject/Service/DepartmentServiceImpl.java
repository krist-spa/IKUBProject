package com.example.IKUBProject.Service;


import com.example.IKUBProject.DTO.DepartmentDTO;
import com.example.IKUBProject.Entity.Department;
import com.example.IKUBProject.Model.AjaxResponse;
import com.example.IKUBProject.Repository.DepartmentRepository;
import com.example.IKUBProject.Model.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    @Override
    public DataTable getDepartments(Integer draw, Integer start, Integer length) {
        DataTable dataTable = new DataTable();
        dataTable.setDraw(draw);
        long count = departmentRepository.count();
        dataTable.setRecordsTotal(count);
        dataTable.setRecordsFiltered(count);
        dataTable.setData(departmentRepository.findAll(PageRequest.of(start, length)).getContent());
        return dataTable;
    }


    @Override
    public AjaxResponse createDepartment(DepartmentDTO departmentDTO) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Department department = new Department();
            if (departmentDTO.getName() != null && !departmentDTO.getName().trim().isEmpty()) {
                department.setName(departmentDTO.getName());
                department.setCreatedtime(new Date());
                departmentRepository.save(department);
                log.info("New Department created: " + department.toString());
                ajaxResponse.setMessage("Department created with success!");
                ajaxResponse.setValue(department);
            } else {
                ajaxResponse.setMessage("Required data is missing!");
                ajaxResponse.setError(true);
            }
        } catch (Exception ex) {
            ajaxResponse.setMessage(ex.getMessage());
            ajaxResponse.setError(true);
        }
        return ajaxResponse;
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        return departmentOptional.orElse(null);
    }

    @Override
    public AjaxResponse deleteDepartment(Integer departmentid) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Department department = findDepartmentById(departmentid);
            if (department != null) {
                if (department.getEmployeeSet().isEmpty()) {
                    departmentRepository.delete(department);
                    log.info("Department with id: " + departmentid + " deleted successfully. ");
                } else {
                    ajaxResponse.setMessage("Department,has registered employyes and it can not be deleted!");
                    ajaxResponse.setError(true);
                }
            } else {
                ajaxResponse.setMessage("Department not found!");
                ajaxResponse.setError(true);
            }
        } catch (Exception ex) {
            ajaxResponse.setMessage(ex.getMessage());
            ajaxResponse.setError(true);
        }
        return ajaxResponse;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public AjaxResponse updateDepartment(DepartmentDTO departmentDTO) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Department department = findDepartmentById(departmentDTO.getDepartmentid());
            if (department != null) {
                if (departmentDTO.getName() != null && !departmentDTO.getName().trim().isEmpty()) {
                    department.setName(departmentDTO.getName());
                    departmentRepository.save(department);
                    log.info("Department updated successfully:" + department.toString());
                    ajaxResponse.setMessage("Department modified with success!");
                    ajaxResponse.setValue(department);
                } else {
                    ajaxResponse.setMessage("Required data is missing!");
                    ajaxResponse.setError(true);
                }
            } else {
                ajaxResponse.setMessage("Department not found!");
                ajaxResponse.setError(true);
            }
        } catch (Exception ex) {
            ajaxResponse.setMessage(ex.getMessage());
            ajaxResponse.setError(true);
        }
        return ajaxResponse;
    }
}
