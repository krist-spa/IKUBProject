package com.example.IKUBProject.Controller;

import com.example.IKUBProject.Service.DepartmentService;
import com.example.IKUBProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

    @GetMapping("/getEmployeeModal")
    public String getEmployeeModal(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employeeModal";
    }

    @GetMapping("/getDepartmentModal")
    public String getDepartmentModal() {
        return "departmentModal";
    }



    @GetMapping("/getModifyEmployeeModal/{employeeid}")
    public String getModifyEmployeeModal(Model model, @PathVariable Integer employeeid) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("employeeid", employeeid);
        model.addAttribute("employee", employeeService.findEmployeeById(employeeid));
        return "employeeModal";
    }

    @GetMapping("/getModifyDepartmentModal/{departmentid}")
    public String getModifyDepartmentModal(Model model,@PathVariable Integer departmentid) {
        model.addAttribute("department", departmentService.findDepartmentById(departmentid));
        model.addAttribute("departmentid", departmentid);
        return "departmentModal";
    }

}
