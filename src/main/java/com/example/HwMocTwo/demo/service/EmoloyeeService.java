package com.example.HwMocTwo.demo.service;

import com.example.HwMocTwo.demo.employee.Employee;

import java.util.Collection;

public interface EmoloyeeService {
    Employee add(String firstName, String lastName, Integer department, Integer salary);

    Employee remove(String firstName, String lastName, Integer department, Integer salary);

    Employee find(String firstName, String lastName, Integer department, Integer salary);

    Collection<Employee> getEmployees();
}
