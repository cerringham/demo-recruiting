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
        return id != null;
    }

    public Boolean validateStringAlphaNumericSpace(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return StringUtils.isAlphanumericSpace(name);
    }

    public Boolean validateStringNotNullOrEmpty(String s) {
        return !StringUtils.isEmpty(s);
    }

    public Boolean validateStringAlphaSpace(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return StringUtils.isAlphaSpace(s);
    }

    public Boolean validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public Boolean validatePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return false;
        }
        if (StringUtils.startsWith(phoneNumber, "+")) {
            phoneNumber = phoneNumber.replace("+", "");
        }
        return StringUtils.isNumericSpace(phoneNumber);
    }

    public Boolean validateAge(String birthDate) {
        if (StringUtils.isEmpty(birthDate)) {
            return false;
        }

        LocalDate parsedBirthDate = parsingUtility.parseStringToLocalDate(birthDate);
        if (parsedBirthDate == null) {
            return false;
        }
        Period period = Period.between(parsedBirthDate, LocalDate.now());
        int eta = period.getYears();

        return eta >= 18;
    }
}
