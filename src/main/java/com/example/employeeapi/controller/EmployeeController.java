package com.example.employeeapi.controller;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable Integer id) {

        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
@PostMapping("/api/employees")
public ResponseEntity<Employee> createEmployee(
        @Valid @RequestBody Employee employee) {

    Employee savedEmployee =
            employeeService.createEmployee(employee);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(savedEmployee);
}


@PutMapping("/api/employees/{id}")
public ResponseEntity<Employee> updateEmployee(
        @PathVariable Integer id,
        @Valid @RequestBody Employee updatedEmployee) {

    return employeeService.updateEmployee(id, updatedEmployee)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}


@DeleteMapping("/api/employees/{id}")
public ResponseEntity<Void> deleteEmployee(
        @PathVariable Integer id) {

    boolean deleted = employeeService.deleteEmployee(id);

    if (deleted) {
        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.notFound().build();
}


}