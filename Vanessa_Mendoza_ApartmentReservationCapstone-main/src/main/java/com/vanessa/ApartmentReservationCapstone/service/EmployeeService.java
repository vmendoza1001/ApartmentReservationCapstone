package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.exception.EmployeeNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.Employee;
import com.vanessa.ApartmentReservationCapstone.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByFullName(String fullName) throws EmployeeNotFoundException {
        return employeeRepository.findByFullName(fullName);
    }

    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        if (!employeeRepository.existsByFullName(employee.getFullName())) {
            throw new EmployeeNotFoundException(employee.getFullName());
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeByFullName(String fullName) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findByFullName(fullName);
        if (employee == null) {
            throw new EmployeeNotFoundException(fullName);
        }
        employeeRepository.delete(employee);
    }
}
