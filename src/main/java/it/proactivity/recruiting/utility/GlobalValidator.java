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
            return false;
        }
        return true;
    }

    public Boolean validateStringAlphaNumericSpace(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        if (StringUtils.isAlphanumericSpace(name)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validateStringAlphaSpace(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        if (StringUtils.isAlphaSpace(s)) {
            return true;
        } else {
            return false;
        }
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
        if (StringUtils.isNumericSpace(phoneNumber)) {
            return true;
        }
        return false;
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
        Integer eta = period.getYears();

        if (eta < 18) {
            return false;
        }
        return true;
    }
}
