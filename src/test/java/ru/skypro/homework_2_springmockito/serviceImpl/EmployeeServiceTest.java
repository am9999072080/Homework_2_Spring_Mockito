package ru.skypro.homework_2_springmockito.serviceImpl;


import org.junit.jupiter.api.Test;
import ru.skypro.homework_2_springmockito.exceptions.EmployeeAlreadyAddedException;
import ru.skypro.homework_2_springmockito.exceptions.EmployeeNotFoundException;
import ru.skypro.homework_2_springmockito.model.Employee;
import ru.skypro.homework_2_springmockito.service.impl.EmployeeServiceImpl;
import ru.skypro.homework_2_springmockito.service.impl.EmployeeValidationServiceImpl;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.homework_2_springmockito.serviceImpl.EmployeeServiceConstants.*;

public class EmployeeServiceTest {

    private final EmployeeValidationServiceImpl validationService = new EmployeeValidationServiceImpl();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(validationService);
    @Test
    public void shouldAddEmployee(){
        Employee employee = new Employee(FIRST_NAME1, LAST_NAME1, SALARY, DEPARTMENT_ID_1);
        assertFalse(employeeService.findAll().contains(employee));
        assertEquals(0, employeeService.findAll().size());

        Employee addedEmployee = employeeService.add(FIRST_NAME1, LAST_NAME1);
        assertEquals(employee, addedEmployee);
        assertEquals(1, employeeService.findAll().size());
        assertTrue(employeeService.findAll().contains(employee));
    }
    @Test
    public void shouldThrowEmployeeAlreadyAddedException(){
        Employee employee = employeeService.add(FIRST_NAME1, LAST_NAME1);
        assertTrue(employeeService.findAll().contains(employee));
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.add(FIRST_NAME1, LAST_NAME1));
    }
    @Test
    public void shouldFindExistEmployee(){
        Employee employee = employeeService.add (FIRST_NAME1, LAST_NAME1);
        assertEquals(employee, employeeService.find(FIRST_NAME1, LAST_NAME1));
    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployee() {
        assertEquals(0, employeeService.findAll().size());
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.find(FIRST_NAME1, LAST_NAME1));
    }
    @Test
    public void shouldRemoveExistEmployee(){
        Employee addedEmployee = employeeService.add(FIRST_NAME1, LAST_NAME1);
        assertEquals(1, employeeService.findAll().size());
        assertTrue(employeeService.findAll().contains(addedEmployee));

        Employee removedEmployee=employeeService.remove(FIRST_NAME1, LAST_NAME1);
        assertEquals(addedEmployee, removedEmployee);
        assertEquals(0, employeeService.findAll().size());
        assertFalse(employeeService.findAll().contains(addedEmployee));
    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemoveEmployee() {
        assertEquals(0, employeeService.findAll().size());
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.remove(FIRST_NAME1, LAST_NAME1));
    }
    @Test
    public void shouldReturnAllEmployees(){
        Employee employee1 = employeeService.add(FIRST_NAME1, LAST_NAME1);
        Employee employee2 = employeeService.add(FIRST_NAME2, LAST_NAME2);

        Collection<Employee> addedEmployees = employeeService.findAll();
        assertIterableEquals(List.of(employee1, employee2), addedEmployees);
    }
}
