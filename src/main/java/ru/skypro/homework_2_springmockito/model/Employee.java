package ru.skypro.homework_2_springmockito.model;


import java.util.Objects;
import java.util.Random;

public class Employee {
    private String firstName;
    private String lastName;
    private int salary;
    private int departmentId;


    public Employee(String firstName, String lastName) {
        Random random = new Random();
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = random.nextInt(9000) + 1000;
        this.departmentId = random.nextInt(5) + 1;
    }

    public Employee(String firstName, String lastName, int salary, int departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getFullName() {//возврашение имени и фамилии
        return firstName + " " + lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
