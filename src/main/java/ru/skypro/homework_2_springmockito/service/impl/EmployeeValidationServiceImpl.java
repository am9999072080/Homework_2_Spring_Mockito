package ru.skypro.homework_2_springmockito.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework_2_springmockito.exceptions.InvalidEmployeeDataException;
import ru.skypro.homework_2_springmockito.service.EmployeeValidationService;

import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class EmployeeValidationServiceImpl implements EmployeeValidationService {
    @Override
    public void validate(String firstName, String lastName) {
        validateName(firstName);
        validateName(lastName);
    }

    private void validateName(String name) {
        if (isBlank(name) || !isAlpha(name)) {
            throw new InvalidEmployeeDataException("Некорректное значение имени։ " + name);
        }
    }
}
