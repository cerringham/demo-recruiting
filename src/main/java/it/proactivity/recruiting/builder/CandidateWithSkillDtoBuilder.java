package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Curriculum;
import it.proactivity.recruiting.model.Expertise;
import it.proactivity.recruiting.model.dto.CandidateWithSkillDto;
import it.proactivity.recruiting.model.dto.CurriculumDto;
import it.proactivity.recruiting.model.dto.ExpertiseDto;
import it.proactivity.recruiting.model.dto.SkillDto;

import java.util.List;
import java.util.Set;

public class CandidateWithSkillDtoBuilder {
    private Long id;
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

    private String birthDate;

    private ExpertiseDto expertise;

    private Set<SkillDto> skills;

    private CandidateWithSkillDtoBuilder(String name) {
        this.name = name;
    }

    public static CandidateWithSkillDtoBuilder newBuilder(String name) {
        return new CandidateWithSkillDtoBuilder(name);
    }

    public CandidateWithSkillDtoBuilder fiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
        return this;
    }

    public CandidateWithSkillDtoBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public CandidateWithSkillDtoBuilder cityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
        return this;
    }

    public CandidateWithSkillDtoBuilder countryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
        return this;
    }

    public CandidateWithSkillDtoBuilder cityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
        return this;
    }

    public CandidateWithSkillDtoBuilder streetOfResidence(String streetOfResidence) {
        this.streetOfResidence = streetOfResidence;
        return this;
    }

    public CandidateWithSkillDtoBuilder regionOfResidence(String regionOfResidence) {
        this.regionOfResidence = regionOfResidence;
        return this;
    }

    public CandidateWithSkillDtoBuilder countryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
        return this;
    }

    public CandidateWithSkillDtoBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CandidateWithSkillDtoBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CandidateWithSkillDtoBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public CandidateWithSkillDtoBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CandidateWithSkillDtoBuilder birthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    public CandidateWithSkillDtoBuilder expertise(ExpertiseDto expertise) {
        this.expertise = expertise;
        return this;
    }

    public CandidateWithSkillDtoBuilder skills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }

    public CandidateWithSkillDto build() {
        return new CandidateWithSkillDto(id, fiscalCode, name, surname, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence,
                regionOfResidence, countryOfResidence, email, phoneNumber, gender, isActive, birthDate, expertise, skills);
    }
}
