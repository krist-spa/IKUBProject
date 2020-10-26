package com.example.IKUBProject.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EmployeeDTO {

    private Integer employeeid;
    private String firstname;
    private String lastname;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;
    private String position;
    private Integer departmentid;
    private Double salary;

    public Integer getEmployeeid() {
        return employeeid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPosition() {
        return position;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public Double getSalary() {
        return salary;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public EmployeeDTO(Integer employeeid, String firstname, String lastname, LocalDate birthday, String position, Integer departmentid, Double salary) {
        this.employeeid = employeeid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.position = position;
        this.departmentid = departmentid;
        this.salary = salary;
    }

    public boolean isValidEmployeeDTO() {
        if (this.getFirstname() == null || this.getFirstname().trim().isEmpty()
                || this.getLastname() == null || this.getLastname().trim().isEmpty()
                || this.getDepartmentid() == null) {
            return false;
        } else {
            return true;
        }
    }
}
