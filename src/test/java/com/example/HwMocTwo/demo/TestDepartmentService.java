package com.example.HwMocTwo.demo;


import com.example.HwMocTwo.demo.employee.Employee;
import com.example.HwMocTwo.demo.service.DepartmentServiceImpl;
import com.example.HwMocTwo.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.example.HwMocTwo.demo.TestEmployeesConstant.DEPARTMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestDepartmentService {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;


    private List<Employee> employees = List.of(
            new Employee("Jonny", "Soup", 1, 5000),
            new Employee("Saymon", "Ghost", 1, 6000),
            new Employee("Price", "Cap", 2, 4500));

    @Test
    void MaxSalaryTest() {
        when(employeeService.getEmployees()).thenReturn(employees);

        assertEquals(employees.get(1), departmentService.maxSalary(DEPARTMENT));
    }

    @Test
    void MinSalaryTest() {
        when(employeeService.getEmployees()).thenReturn(employees);

        assertEquals(employees.get(0), departmentService.minSalary(DEPARTMENT));
    }

    @Test
    void findMinSalaryEmployeeNotFoundException() {
        when(employeeService.getEmployees()).thenReturn(Collections.emptyList());

        assertThrows(RuntimeException.class, () -> departmentService.minSalary(DEPARTMENT));
    }

    @Test
    void returnAllEmployeesInDepartment() {
        when(employeeService.getEmployees()).thenReturn(employees);

        assertEquals(List.of(employees.get(0), employees.get(1)), departmentService.getAllByDepartment(DEPARTMENT));
    }

    @Test
    void getAllEmployeesInDepartmentEmpty() {
        when(employeeService.getEmployees()).thenReturn(Collections.emptyList());

        assertEquals(List.of(), departmentService.getAllByDepartment(DEPARTMENT));
    }

    @Test
    void getAllEmployeeInDepartments() {
        when(employeeService.getEmployees()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                1, List.of(employees.get(0), employees.get(1)),
                2, List.of(employees.get(2)));

        assertEquals(expectedMap, departmentService.getAll());
    }

    @Test
    void getAllReturnEmptyListWhenEmployeeIsNotFoundInMap() {
        when(employeeService.getEmployees()).thenReturn(Collections.emptyList());
        assertEquals(Map.of(), departmentService.getAll());
    }
}
