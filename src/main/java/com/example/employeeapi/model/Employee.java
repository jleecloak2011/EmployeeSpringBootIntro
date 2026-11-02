package com.example.employeeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Employee name is required")
    @Size(max = 150, message = "Name cannot exceed 150 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Department is required")
    @Size(max = 150, message = "Department cannot exceed 150 characters")
    @Column(name = "department", nullable = false)
    private String department;

    public Employee() {
    }

    public Employee(Integer id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Employee(String name, String department) {
    this.name = name;
    this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}