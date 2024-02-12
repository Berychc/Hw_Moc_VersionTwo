package com.example.HwMocTwo.demo.Controller;

import com.example.HwMocTwo.demo.employee.Employee;
import com.example.HwMocTwo.demo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee addEmployee(@RequestParam  String firstName,
                                @RequestParam String lastName,
                                @RequestParam int department,
                                @RequestParam int salary) {
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam int department,
                                   @RequestParam int salary) {
        return employeeService.remove(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam int department,
                                 @RequestParam int salary) {
        return employeeService.find(firstName, lastName, department, salary);
    }

    @GetMapping("/collection")
    public Collection<Employee> getCollection() {
        return employeeService.getEmployees();
    }
}
