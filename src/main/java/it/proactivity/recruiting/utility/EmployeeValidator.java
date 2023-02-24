package it.proactivity.recruiting.utility;

import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {

    public Boolean validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The id can't be null");
        }
        return true;
    }
}
