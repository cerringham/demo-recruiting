package it.proactivity.recruiting;

import it.proactivity.recruiting.utility.GlobalValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class GlobalValidatorTest {

    @Autowired
    GlobalValidator globalValidator;

    @Test
    void validateFiscalCodeTest() {
        String code = "TUFDNSDI6478DGGF";
        assertTrue(globalValidator.validateFiscalCode(code));
        String notCode = "TRUREY&)/$)&FDSGDV";
        assertFalse(globalValidator.validateFiscalCode(notCode));
    }

    @Test
    void validateEmailTest() {
        String email = "veronicazuniga3@gmail.com";
        assertTrue(globalValidator.validateEmail(email));
        String email1 = "(/=&&%$2.gmail.com";
        assertFalse(globalValidator.validateEmail(email1));
    }

    @Test
    void validateBirthDateTest() {
        String date = "2005-02-28";
        assertTrue(globalValidator.validateBirthDate(date));
        String date1 = "2005-03-01";
        assertFalse(globalValidator.validateBirthDate(date1));
    }

    @Test
    void validatePhoneNumber() {
        String phoneNumber = "+37884564980";
        assertTrue(globalValidator.validatePhoneNumber(phoneNumber));
        assertNotNull(globalValidator.validatePhoneNumber(phoneNumber));
    }
}
