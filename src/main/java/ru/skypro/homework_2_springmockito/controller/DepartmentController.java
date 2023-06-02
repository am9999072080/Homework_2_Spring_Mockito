package ru.skypro.homework_2_springmockito.controller;


import org.springframework.web.bind.annotation.*;
import ru.skypro.homework_2_springmockito.model.Employee;
import ru.skypro.homework_2_springmockito.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;

    }

    @GetMapping("{id}/salary/max")
    public Employee findEmployeeMaxSalary(@RequestParam int departmentId) {
        return departmentService.findEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public Employee findEmployeeMinSalary(@RequestParam int departmentId) {
        return departmentService.findEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public Integer calculateSalariesByDepartment(@RequestParam int departmentId, @PathVariable String id) {
        return departmentService.calculateSalariesByDepartment(departmentId);
    }

    @GetMapping(value = "{id}/employees")
    public Collection<Employee> findEmployeesByDepartment(@RequestParam int departmentId) {
        return departmentService.findEmployeeByDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return departmentService.findEmployeeByDepartment();
    }
}
