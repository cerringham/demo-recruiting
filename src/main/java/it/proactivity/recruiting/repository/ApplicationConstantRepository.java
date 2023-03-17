package it.proactivity.recruiting.repository;

import it.proactivity.recruiting.model.ApplicationConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationConstantRepository extends JpaRepository<ApplicationConstant, Long> {


    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'company_role_name'")
    String companyRoleNameMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'job_position_minimum_skill'")
    String jobPositionMinimumSkills();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'job_position_title'")
    String jobPositionTitleMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'job_position_area'")
    String jobPositionAreaMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'job_position_city'")
    String jobPositionCityMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'job_position_region'")
    String jobPositionRegionMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'job_position_country'")
    String jobPositionCountryMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'company_name'")
    String companyNameMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'job_position_status_name'")
    String jobPositionStatusNameMaxLength();

    @Query("SELECT a.value FROM ApplicationConstant a WHERE a.name = 'max_companies'")
    String maxCompanies();

}
