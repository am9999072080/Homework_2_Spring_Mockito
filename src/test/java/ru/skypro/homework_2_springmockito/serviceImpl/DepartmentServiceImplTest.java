package ru.skypro.homework_2_springmockito.serviceImpl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework_2_springmockito.exceptions.EmployeeNotFoundException;
import ru.skypro.homework_2_springmockito.model.Employee;
import ru.skypro.homework_2_springmockito.service.EmployeeService;
import ru.skypro.homework_2_springmockito.service.impl.DepartmentServiceImpl;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.skypro.homework_2_springmockito.serviceImpl.EmployeeServiceConstants.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void shouldReturnEmployeeWithMaxSalaryByDepartmentId(){
        when(employeeService.findAll()).thenReturn(EMPLOYEES);

        assertEquals(MAX_SALARY_EMPLOYEE, departmentService.findEmployeeWithMaxSalary(DEPARTMENT_ID_1));
    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeWithMaxSalaryByDepartmentId(){
        when(employeeService.findAll()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class,
                () ->departmentService.findEmployeeWithMaxSalary(DEPARTMENT_ID_1));
    }
    @Test
    public void shouldReturnEmployeeWithMaxSalaryByOtherDepartmentId(){
        when(employeeService.findAll()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class,
                () ->departmentService.findEmployeeWithMaxSalary(DEPARTMENT_ID_2));
    }
    @Test
    public void shouldReturnEmployeeWithMinSalaryByDepartmentId(){
        when(employeeService.findAll()).thenReturn(EMPLOYEES);

        assertEquals(MIN_SALARY_EMPLOYEE, departmentService.findEmployeeWithMinSalary(DEPARTMENT_ID_1));
    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeWithMinSalaryByDepartmentId(){
        when(employeeService.findAll()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class,
                () ->departmentService.findEmployeeWithMinSalary(DEPARTMENT_ID_1));
    }
    @Test
    public void shouldReturnEmployeeWithMinSalaryByOtherDepartmentId(){
        when(employeeService.findAll()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class,
                () ->departmentService.findEmployeeWithMinSalary(DEPARTMENT_ID_2));
    }
    @Test
    public void shouldFindEmployeeByDepartmentId(){
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(singletonList(MAX_SALARY_EMPLOYEE), departmentService.findEmployeeByDepartment(DEPARTMENT_ID_1));
        assertEquals(singletonList(DIFFERENT_DEPARTMENT_EMPLOYEE), departmentService.findEmployeeByDepartment(DEPARTMENT_ID_2));
    }
    @Test
    public void shouldReturnEmptyEmployeesMapByDepartments(){
        when(employeeService.findAll()).thenReturn(emptyList());

        assertEquals(emptyMap(), departmentService.findEmployeeByDepartment());
    }
    @Test
    public void shouldReturnNotEmptyEmployeesMapByDepartments(){
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(EMPLOYEES_BY_DEPATRMENTS_MAP, departmentService.findEmployeeByDepartment());
    }

}
