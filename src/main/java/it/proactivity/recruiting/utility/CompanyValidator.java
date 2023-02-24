package it.proactivity.recruiting.utility;

import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {

    public Boolean validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        return true;
    }
}
