package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "company_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private List<Employee> employeeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_role_id")
    private List<Company> companyList;

    @Override
    public String toString() {
        return "CompanyRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", employeeList=" + employeeList +
                ", companyList=" + companyList +
                '}';
    }
}
