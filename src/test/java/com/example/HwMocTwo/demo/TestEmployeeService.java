package com.example.HwMocTwo.demo;

import com.example.HwMocTwo.demo.employee.Employee;
import com.example.HwMocTwo.demo.service.EmoloyeeService;
import com.example.HwMocTwo.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static com.example.HwMocTwo.demo.TestEmployeesConstant.*;
import static com.example.HwMocTwo.demo.TestEmployeesConstant.SALARY;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest
public class TestEmployeeService {

    private final EmoloyeeService employeeService = new EmployeeServiceImpl();

    @Test
    void AddEmployeeTest() {

        Employee expected = new Employee(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);

        assertEquals(0, employeeService.getEmployees().size());
        assertFalse(employeeService.getEmployees().contains(expected));

        Employee actual = employeeService.add(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);

        assertEquals(expected, actual);
        assertEquals(1, employeeService.getEmployees().size());
        assertTrue(employeeService.getEmployees().contains(expected));

    }

    @Test
    void EmployeeAlreadyAddedExceptionTest() {
        Employee existed = employeeService.add(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);

        assertTrue(employeeService.getEmployees().contains(existed));
        assertThrows(RuntimeException.class, () -> employeeService.add(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY));
    }

    @Test
    void FindEmployeeTest() {
        Employee existed = employeeService.add(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);

        assertEquals(existed, employeeService.find(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY));
    }

    @Test
    void EmployeeNotFoundExceptionTest() {
        assertEquals(0, employeeService.getEmployees().size());

        assertThrows(RuntimeException.class, () -> employeeService.find(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY));
    }

    @Test
    void RemoveEmployeeTest() {
        Employee expected = employeeService.add(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);

        assertEquals(1, employeeService.getEmployees().size());
        assertTrue(employeeService.getEmployees().contains(expected));

        Employee actual = employeeService.remove(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);

        assertEquals(expected, actual);
        assertTrue(employeeService.getEmployees().isEmpty());
        assertFalse(employeeService.getEmployees().contains(expected));
    }

    @Test
    void EmployeeRemoveNotFoundExceptionTest() {
        assertTrue(employeeService.getEmployees().isEmpty());

        assertThrows(RuntimeException.class, () -> employeeService.remove(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY));
    }

    @Test
    void EmptyCollectionTest() {
        assertIterableEquals(emptyList(), employeeService.getEmployees());
    }

    @Test
    void CollectionListTest() {
        Employee employee = employeeService.add(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);

        Employee employee2 = employeeService.add(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT, SALARY);

        Collection<Employee> existed = List.of(employee, employee2);

        Collection<Employee> actual = employeeService.getEmployees();

        assertIterableEquals(existed, actual);
    }
}
