package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.builder.AccountBuilder;
import it.proactivity.recruiting.model.Account;
import it.proactivity.recruiting.model.dto.AddAccountDto;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class AccountUtility {

    public Account createAccount(AddAccountDto dto) {

        try{
            String ashedPassword = hashPassword(dto.getPassword());
            return AccountBuilder.newBuilder(dto.getName())
                    .surname(dto.getSurname())
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .password(ashedPassword)
                    .isActive(true)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    public static String hashPassword(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(plainText.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }
}
