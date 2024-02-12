package com.example.HwMocTwo.demo.Controller;

import com.example.HwMocTwo.demo.employee.Employee;
import com.example.HwMocTwo.demo.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
        private final DepartmentService departmentService;

        public DepartmentController(DepartmentService departmentService) {
            this.departmentService = departmentService;
        }

        @GetMapping("{id}/employees")
        public ResponseEntity<?> getAllByDepartment(@PathVariable("id") Integer department) {
            return ResponseEntity.ok(departmentService.getAllByDepartment(department));
        }

        @GetMapping("/{id}/salary/max")
        public Employee maxSalary(@PathVariable("id") Integer department) {
            return departmentService.maxSalary(department);
        }

        @GetMapping("/{id}/salary/min")
        public Employee minSalary(@PathVariable("id") Integer department) {
            return departmentService.minSalary(department);
        }

        @GetMapping("/{id}/salary/sum")
        public int sumSalaryDepartment(@PathVariable("id") Integer department) {
            return departmentService.sumSalary(department);
        }
        @GetMapping("/employees")
        public Map<Integer, List<Employee>> getAll() {
            return departmentService.getAll();
        }

    }
