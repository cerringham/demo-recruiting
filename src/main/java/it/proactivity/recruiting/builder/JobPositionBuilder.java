package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.Company;
import it.proactivity.recruiting.model.JobPosition;
import it.proactivity.recruiting.model.JobPositionStatus;
import it.proactivity.recruiting.model.SkillLevel;

import java.util.List;

public class JobPositionBuilder {

    private String title;
    private String area;
    private String description;
    private String city;
    private String region;
    private String country;
    private Boolean isActive;
    private Company company;
    private JobPositionStatus jobPositionStatus;
    private List<SkillLevel> skillList;

    private JobPositionBuilder(String title) {
        this.title = title;
    }

    public static JobPositionBuilder newBuilder(String title) {
        return new JobPositionBuilder(title);
    }

    public JobPositionBuilder area(String area) {
        this.area = area;
        return this;
    }

    public JobPositionBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobPositionBuilder city(String city) {
        this.city = city;
        return this;
    }

    public JobPositionBuilder region(String region) {
        this.region = region;
        return this;
    }

    public JobPositionBuilder country(String country) {
        this.country = country;
        return this;
    }

    public JobPositionBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public JobPositionBuilder company(Company company) {
        this.company = company;
        return this;
    }

    public JobPositionBuilder jobPositionStatus(JobPositionStatus jobPositionStatus) {
        this.jobPositionStatus = jobPositionStatus;
        return this;
    }

    public JobPositionBuilder skillList(List<SkillLevel> skillList) {
        this.skillList = skillList;
        return this;
    }

    public JobPosition build() {
        return new JobPosition(title, area, description, city, region, country, isActive, company, jobPositionStatus, skillList);
    }
}
