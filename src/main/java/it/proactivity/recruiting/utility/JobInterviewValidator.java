package it.proactivity.recruiting.utility;

import org.springframework.stereotype.Component;

@Component
public class JobInterviewValidator {

    public Boolean validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id can't be null");
        }
        return true;
    }
}
