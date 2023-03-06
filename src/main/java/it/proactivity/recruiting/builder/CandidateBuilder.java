package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Candidate;
import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CandidateBuilder {

    private String fiscalCode;

    private String name;

    private String surname;

    private String cityOfBirth;

    private String countryOfBirth;

    private String cityOfResidence;

    private String streetOfResidence;

    private String regionOfResidence;

    private String countryOfResidence;

    private String email;

    private String phoneNumber;

    private String gender;

    private Boolean isActive;

    private LocalDate birthDate;

    private Expertise expertise;

    private Set<Curriculum> candidateSkillList;

    private CandidateBuilder(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public static CandidateBuilder newBuilder(String fiscalCode) {
        return new CandidateBuilder(fiscalCode);
    }

    public CandidateBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CandidateBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public CandidateBuilder cityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
        return this;
    }

    public CandidateBuilder countryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
        return this;
    }

    public CandidateBuilder cityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
        return this;
    }

    public CandidateBuilder streetOfResidence(String streetOfResidence) {
        this.streetOfResidence = streetOfResidence;
        return this;
    }

    public CandidateBuilder regionOfResidence(String regionOfResidence) {
        this.regionOfResidence = regionOfResidence;
        return this;
    }

    public CandidateBuilder countryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
        return this;
    }

    public CandidateBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CandidateBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CandidateBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public CandidateBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CandidateBuilder birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    public CandidateBuilder expertise(Expertise expertise) {
        this.expertise = expertise;
        return this;
    }

    public CandidateBuilder curriculumDtoList(Set<Curriculum> candidateSkillList) {
        this.candidateSkillList = candidateSkillList;
        return this;
    }

    public Candidate build() {
        return new Candidate(fiscalCode, name, surname, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence,
                regionOfResidence, countryOfResidence, email, phoneNumber, gender, isActive, birthDate, expertise, candidateSkillList);
    }
}

