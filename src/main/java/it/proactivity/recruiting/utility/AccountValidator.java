package it.proactivity.recruiting.utility;


import it.proactivity.recruiting.model.dto.AddAccountDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
        char[] splittedPassword = password.toCharArray();

        Boolean hasUpper = false;
        Boolean hasNumber = false;
        Boolean hasSpecialChar = false;
        for (Character c : splittedPassword) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        return hasUpper && hasNumber && hasSpecialChar;
    }

    private Boolean validateNameAndSurname(String name, String surname) {
        return (globalValidator.vaidateStringAlpha(name) && name.length() < NAME_AND_SURNAME_MAX_LENGTH) &&
                (globalValidator.vaidateStringAlpha(surname) && surname.length() < NAME_AND_SURNAME_MAX_LENGTH);
    }

    public Boolean validateAddAccountDto(AddAccountDto dto) {
        if (dto == null) {
            return false;
        }

        return validatePassword(dto.getPassword()) && validateNameAndSurname(dto.getName(), dto.getSurname()) &&
                validateAndCheckIfUsernameAndEmailAreSame(dto.getUsername(), dto.getEmail());
    }


}
