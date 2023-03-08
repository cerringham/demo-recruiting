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
    void validateNameAndSurnamePositiveTest() {
        assertTrue(globalValidator.validateStringAlphaSpace("Alessio"));
        assertTrue(globalValidator.validateStringAlphaSpace("Alessio Giacomo"));
    }

    @Test
    void validateNameAndSurnameNegativeTest() {
        assertFalse(globalValidator.validateStringAlphaSpace("Alessio11"));
        assertFalse(globalValidator.validateStringAlphaSpace("Alessio, Giacomo"));
    }

    @Test
    void validateEmailPositiveTest() {
        assertTrue(globalValidator.validateEmail("alessio.cassarino@proactivity.it"));
    }


    @Test
    void validateEmailNegativeTest() {
        assertFalse(globalValidator.validateEmail("alessio cassarino@proactivity.it"));
    }

    @Test
    void validatePhoneNumberPositiveTest() {
        assertTrue(globalValidator.validatePhoneNumber("+39 3387676767"));
    }

    @Test
    void validatePhoneNumberNegativeTest() {
        assertFalse(globalValidator.validatePhoneNumber("+a 3387676767"));
        assertFalse(globalValidator.validatePhoneNumber("@39 3387676767"));
    }

    @Test
    void validateAgePositiveTest() {
        assertTrue(globalValidator.validateAge("1995-11-16"));
    }

    @Test
    void validateAgeNegativeTest() {
        assertFalse(globalValidator.validateAge("2010-11-16"));
    }

}
