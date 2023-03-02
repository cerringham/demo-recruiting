package it.proactivity.recruiting.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class GlobalValidator {

    @Autowired
    ParsingUtility parsingUtility;

    public Boolean validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return true;
    }

    public Boolean validateStringAlphaSpace(String name, String surname) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)) {
            throw new IllegalArgumentException("Name and surname can't be null or empty");
        }
        if (StringUtils.isAlphaSpace(name) && StringUtils.isAlphaSpace(surname)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public Boolean validatePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("PhoneNumber can't be null or empty");
        }
        if (StringUtils.startsWith(phoneNumber, "+")) {
            phoneNumber = phoneNumber.replace("+", "");
        }
        if (StringUtils.isNumericSpace(phoneNumber)) {
            return true;
        }
        return false;
    }

    public Boolean validateAge(String birthDate) {
        if (StringUtils.isEmpty(birthDate)) {
            throw new IllegalArgumentException("Birth date can't be null or empty");
        }

        LocalDate parsedBirthDate = parsingUtility.parseStringToLocalDate(birthDate);
        if (parsedBirthDate == null) {
            throw new IllegalStateException("Impossible to parse the date");
        }
        Period period = Period.between(parsedBirthDate, LocalDate.now());
        Integer eta = period.getYears();

        if (eta < 18) {
            return false;
        }
        return true;
    }
}
