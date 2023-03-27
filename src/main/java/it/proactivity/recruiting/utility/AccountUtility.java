package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.dto.AccountDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class AccountUtility {

    @Autowired
    GlobalValidator globalValidator;


    public Boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password) || password.length() < 6 || password.length() > 12) {
            return false;
        }
        char a =
        if (password.contains())
    }

    public Boolean validateAccountDto(AccountDto accountDto){
        if (accountDto == null || !globalValidator.validateStringAlphaSpace(accountDto.getName()) ||
                !globalValidator.validateStringAlphaSpace(accountDto.getSurname())
                || !globalValidator.validateEmail(accountDto.getEmail()) || !accountDto.getEmail().equals(accountDto.getUsername())||
                StringUtils.isEmpty(accountDto.getPassword()) || accountDto.getIsActive() == false) {
            return false;
        }
        return true;
    }
}
