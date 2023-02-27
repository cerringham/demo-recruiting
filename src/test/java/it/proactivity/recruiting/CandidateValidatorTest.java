package it.proactivity.recruiting;

import it.proactivity.recruiting.utility.CandidateValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
public class CandidateValidatorTest {
    @Autowired
    CandidateValidator candidateValidator;

    @Test
    void validateSkillsPositiveTest() {
        Set<String> skills = new HashSet<>();
        skills.add("Java");

        assertTrue(candidateValidator.validateSkill(skills));
    }

    @Test
    void validateSkillsNegativeTest() {
        Set<String> skills = new HashSet<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidateValidator.validateSkill(null);
            candidateValidator.validateSkill(skills);

        });
        String message = "The Candidate doesn't have any skills";

        assertEquals(message, exception.getMessage());
    }
}
