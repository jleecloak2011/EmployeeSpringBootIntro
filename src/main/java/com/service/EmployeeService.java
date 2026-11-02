package com.example.employeeapi.service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
    return employeeRepository.save(employee);
}

public Optional<Employee> updateEmployee(
        Integer id, Employee updatedEmployee) {

    return employeeRepository.findById(id)
            .map(employee -> {

                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());

                return employeeRepository.save(employee);
            });
}


public boolean deleteEmployee(Integer id) {

    if (!employeeRepository.existsById(id)) {
        return false;
    }

    employeeRepository.deleteById(id);

    return true;
}


}