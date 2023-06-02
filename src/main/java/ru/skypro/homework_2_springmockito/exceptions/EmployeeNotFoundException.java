package ru.skypro.homework_2_springmockito.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skypro.homework_2_springmockito.model.Employee;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException (){
        super ("Сотрудник не найден");
    }
    public  EmployeeNotFoundException(Employee employee){
        super("Сотрудник не найден "+ employee.toString());
    }
}
