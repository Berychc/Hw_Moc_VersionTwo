package com.example.HwMocTwo.demo.service;

import com.example.HwMocTwo.demo.employee.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Employee> getAllByDepartment(Integer department);

    Map<Integer, List<Employee>> getAll();

    Employee maxSalary(Integer department);

    Employee minSalary(Integer department);

    Integer sumSalary(Integer department);
}
