package ru.skypro.homework_2_springmockito.service.impl;


import org.springframework.stereotype.Service;
import ru.skypro.homework_2_springmockito.exceptions.EmployeeNotFoundException;
import ru.skypro.homework_2_springmockito.model.Employee;
import ru.skypro.homework_2_springmockito.service.DepartmentService;
import ru.skypro.homework_2_springmockito.service.EmployeeService;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;


    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee findEmployeeWithMinSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Integer calculateSalariesByDepartment(int departmentId) {

        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .mapToInt(Employee::getSalary).sum();

    }


    @Override
    public Collection<Employee> findEmployeeByDepartment(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .sorted(comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(toList());
    }

    @Override
    public Map<Integer, List<Employee>> findEmployeeByDepartment() {
        return employeeService.findAll().stream()
                .sorted(comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(groupingBy(Employee::getDepartmentId));
    }
}
