package ru.skypro.homework_2_springmockito.serviceImpl;


import ru.skypro.homework_2_springmockito.model.Employee;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class EmployeeServiceConstants {
    public static final String FIRST_NAME1 = "Ivan";
    public static final String LAST_NAME1 = "Ivanov";
    public static final String FIRST_NAME2 = "Sergey";
    public static final String LAST_NAME2 = "Sergeev";
    public static int SALARY = 1_000;
    public static int MAX_SALARY = 10_000;
    public static int DEPARTMENT_ID_1 = 1;
    public static int DEPARTMENT_ID_2 = 2;
    public static final Employee MAX_SALARY_EMPLOYEE = new Employee(FIRST_NAME1, LAST_NAME1, MAX_SALARY, DEPARTMENT_ID_1);
    public static final Employee MIN_SALARY_EMPLOYEE = new Employee(FIRST_NAME2, LAST_NAME2, SALARY, DEPARTMENT_ID_1);
    public static final Employee DIFFERENT_DEPARTMENT_EMPLOYEE = new Employee(FIRST_NAME2, LAST_NAME2, SALARY, DEPARTMENT_ID_2);
    public static final List<Employee> EMPLOYEES = List.of(MAX_SALARY_EMPLOYEE, MIN_SALARY_EMPLOYEE);
    public static final List<Employee> DIFFERENT_DEPARTMENT_EMPLOYEES = List.of(MAX_SALARY_EMPLOYEE, DIFFERENT_DEPARTMENT_EMPLOYEE);
    public static final Map<Integer, List<Employee>> EMPLOYEES_BY_DEPATRMENTS_MAP = DIFFERENT_DEPARTMENT_EMPLOYEES.stream()
            .collect(groupingBy(Employee::getDepartmentId));
}
