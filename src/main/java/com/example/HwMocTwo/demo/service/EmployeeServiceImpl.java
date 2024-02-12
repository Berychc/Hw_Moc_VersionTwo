package com.example.HwMocTwo.demo.service;

import com.example.HwMocTwo.demo.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmoloyeeService {

    private final Map<String, Employee> employeeBook;

    public EmployeeServiceImpl() {
        this.employeeBook = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, Integer department, Integer salary) {
        validateInput(firstName,lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(employee.getFullName())) {
            throw new RuntimeException();
        }

        employeeBook.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, Integer department, Integer salary) {
        validateInput(firstName,lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(employee.getFullName())) {
            return employeeBook.remove(employee.getFullName());
        }
        throw new RuntimeException();
    }

    @Override
    public Employee find(String firstName, String lastName, Integer department, Integer salary) {
        validateInput(firstName,lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(employee.getFullName())) {
            return employeeBook.get(employee.getFullName());
        }
        throw new RuntimeException();

    }


    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employeeBook.values());
    }

    public void validateInput(String firstName, String lastName){
        if (!(isAlpha(firstName) && isAlpha(lastName))){
            throw new RuntimeException();
        }
    }
}
