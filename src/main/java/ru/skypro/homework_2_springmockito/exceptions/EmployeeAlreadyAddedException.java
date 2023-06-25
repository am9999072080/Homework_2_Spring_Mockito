package ru.skypro.homework_2_springmockito.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skypro.homework_2_springmockito.model.Employee;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException(Employee employee) {
        super("Такой сотрудник уже сушествует " + employee.toString());
    }
}

