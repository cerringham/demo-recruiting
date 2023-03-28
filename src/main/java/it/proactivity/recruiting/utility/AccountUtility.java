package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.AccountBuilder;
import it.proactivity.recruiting.model.AccessToken;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AccountDto;
import it.proactivity.recruiting.model.dto.LoginDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccountUtility {

    @Autowired
    GlobalValidator globalValidator;

    public Boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,12}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public Boolean validateAccountDto(AccountDto accountDto){
        if (accountDto == null){
            return false;
        } else if (!globalValidator.validateStringAlphaSpace(accountDto.getName()) ||
                !globalValidator.validateStringAlphaSpace(accountDto.getSurname())
                || !globalValidator.validateEmail(accountDto.getEmail()) || !accountDto.getEmail().equals(accountDto.getUsername())||
                !validatePassword(accountDto.getPassword()) || accountDto.getIsActive() == false) {
            return false;
        }
        return true;
    }

    public String correctPassword(String password) {
        try {
            String correctPassword = hashPassword(password);
            return correctPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hashPassword(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(plainText.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }

    public Boolean validateAccountForLogin(LoginDto loginDto) {
        if (loginDto == null) {
            return false;
        }else if (StringUtils.isEmpty(loginDto.getUsername()) || StringUtils.isEmpty(loginDto.getPassword())) {
            return false;
        }
        return true;
    }
}