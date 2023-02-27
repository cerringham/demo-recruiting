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
        assertTrue(globalValidator.validateNameAndSurname("Alessio", "Cassarino"));
        assertTrue(globalValidator.validateNameAndSurname("Alessio Giacomo", "Cassarino"));
    }

    @Test
    void validateNameAndSurnameNegativeTest() {
        assertFalse(globalValidator.validateNameAndSurname("Alessio11", "Cassarino"));
        assertFalse(globalValidator.validateNameAndSurname("Alessio, Giacomo", "Cassarino"));
    }

    @Test
    void validateNameAndSurnameNullOrEmptyNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            globalValidator.validateNameAndSurname(null, null);
            globalValidator.validateNameAndSurname("", "");
        });

        String message = "Name and surname can't be null or empty";
        assertEquals(message, exception.getMessage());
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
    void validateEmailNullOrEmptyNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            globalValidator.validateEmail(null);
            globalValidator.validateEmail("");
        });

        String message = "Email can't be null or empty";
        assertEquals(message, exception.getMessage());
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
    void validatePhoneNumberNullOrEmptyNegativeTest() {
       Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           globalValidator.validatePhoneNumber(null);
           globalValidator.validatePhoneNumber("");
       });

       String message = "PhoneNumber can't be null or empty";

       assertEquals(message, exception.getMessage());
    }

    @Test
    void validateAgePositiveTest() {
        assertTrue(globalValidator.validateAge("1995-11-16"));
    }

    @Test
    void validateAgeNegativeTest() {
        assertFalse(globalValidator.validateAge("2010-11-16"));
    }

    @Test
    void validateAgeNullOrEmptyNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            globalValidator.validateAge(null);
            globalValidator.validateAge("");
        });

        String message = "Birth date can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void validateAgeWrongFormatNegativeTest() {
        Exception exception = assertThrows(IllegalStateException.class, () ->{
            globalValidator.validateAge("1995/12/12");

        });

        String message = "Impossible to parse the date";

        assertEquals(message, exception.getMessage());
    }
}
