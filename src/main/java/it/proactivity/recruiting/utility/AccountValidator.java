package it.proactivity.recruiting.utility;


import it.proactivity.recruiting.model.dto.AddAccountDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class AccountValidator {

    private static final Integer EMAIL_MAX_LENGTH = 150;


    private static final Integer USERNAME_MAX_LENGTH = 150;

    private static final Integer PASSWORD_MAX_LENGTH = 12;

    private static final Integer PASSWORD_MIN_LENGTH = 6;

    private static final Integer NAME_AND_SURNAME_MAX_LENGTH = 50;

    @Autowired
    GlobalValidator globalValidator;

    private Boolean validateAndCheckIfUsernameAndEmailAreSame(String username, String email) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email)) {
            return false;
        }
        EmailValidator emailValidator = EmailValidator.getInstance();

        if ((!emailValidator.isValid(email) || email.length() > EMAIL_MAX_LENGTH) ||
                (!emailValidator.isValid(username) || username.length() > USERNAME_MAX_LENGTH)) {
            return false;
        }

        return email.equals(username);
    }

    private Boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }

        if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(?=.*'\\d)(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,12}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private Boolean validateNameAndSurname(String name, String surname) {
        return (globalValidator.validateStringAlpha(name) && name.length() < NAME_AND_SURNAME_MAX_LENGTH) &&
                (globalValidator.validateStringAlpha(surname) && surname.length() < NAME_AND_SURNAME_MAX_LENGTH);
    }

    public Boolean validateAddAccountDto(AddAccountDto dto) {
        if (dto == null) {
            return false;
        }

        return validatePassword(dto.getPassword()) && validateNameAndSurname(dto.getName(), dto.getSurname()) &&
                validateAndCheckIfUsernameAndEmailAreSame(dto.getUsername(), dto.getEmail());
    }

    public Boolean validateLoginDto(LoginDto dto) {
        return dto != null;
    }
}
