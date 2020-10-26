package com.example.IKUBProject.DTO;

public class DepartmentDTO {

    private Integer departmentid;
    private String name;

    public DepartmentDTO() {
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public String getName() {
        return name;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentDTO(Integer departmentid, String name) {
        this.departmentid = departmentid;
        this.name = name;
    }

    public DepartmentDTO(String name) {
        this.name = name;
    }
}
