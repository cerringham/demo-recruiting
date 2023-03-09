package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company_role")
@Getter
@Setter
@NoArgsConstructor
public class CompanyRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_role_id")
    private Set<Employee> employeeList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyRole that = (CompanyRole) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(isActive, that.isActive) && Objects.equals(employeeList, that.employeeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive, employeeList);
    }
}
