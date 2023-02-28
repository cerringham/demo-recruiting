package it.proactivity.recruiting.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class GlobalValidator {

    public Boolean validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return true;
    }

    public Boolean validateFiscalCode(String fiscalCode) {
        if (StringUtils.isEmpty(fiscalCode)) {
            return false;
        }
        if (fiscalCode.length() != 16) {
            return false;
        }
        return StringUtils.isAlphanumeric(fiscalCode);
    }

    public Boolean validateNameOrSurname(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return StringUtils.isAlphaSpace(name);
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
        if (phoneNumber.startsWith("+")) {
            return true;
        }
        if (StringUtils.isNumeric(phoneNumber)) {
            return true;
        }
        return false;
    }

    public Boolean validateBirthDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return false;
        }
        if (ParsingUtility.parseStringToDate(date) != null) {
            Calendar today = Calendar.getInstance();
            int thisYear = today.get(Calendar.YEAR);
            int birthYear = Integer.parseInt(date.substring(0, 4));

            int age = thisYear - birthYear;
            return (age >= 18);
        }
        return false;
    }

}
