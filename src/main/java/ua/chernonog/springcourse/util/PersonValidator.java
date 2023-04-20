package ua.chernonog.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.chernonog.springcourse.models.Person;

import java.util.Date;

@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (person.getDateOfBirth() == null) errors.rejectValue("dateOfBirth", "", "Дата " +
                "рождения должна быть в формате дд/мм/гггг");

    }

}

