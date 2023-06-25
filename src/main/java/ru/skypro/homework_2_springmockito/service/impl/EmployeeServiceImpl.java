package ru.skypro.homework_2_springmockito.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework_2_springmockito.exceptions.EmployeeAlreadyAddedException;
import ru.skypro.homework_2_springmockito.exceptions.EmployeeNotFoundException;
import ru.skypro.homework_2_springmockito.model.Employee;
import ru.skypro.homework_2_springmockito.service.EmployeeService;
import ru.skypro.homework_2_springmockito.service.EmployeeValidationService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeValidationService validationService;

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl(EmployeeValidationService validationService) {
        this.validationService = validationService;
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        validationService.validate(firstName, lastName);
        Employee employee = new Employee(capitalize(firstName), capitalize(lastName));
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException(employee);
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(employee.getFullName());
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employee.getFullName());
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
