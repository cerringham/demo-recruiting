package it.proactivity.recruiting.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coo_id")
    private Long cooId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "default_company")
    private Boolean isDefault;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Set<Employee> employeeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<JobPosition> jobPositionList;

    public Company(String name, Long cooId, Boolean isActive) {
        this.name = name;
        this.cooId = cooId;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) &&
                Objects.equals(cooId, company.cooId) && Objects.equals(isActive, company.isActive) &&
                Objects.equals(employeeList, company.employeeList) && Objects.equals(jobPositionList, company.jobPositionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cooId, isActive, employeeList, jobPositionList);
    }
}
