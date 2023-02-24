package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Employee> employeeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<JobPosition> jobPositionList;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cooId=" + cooId +
                ", isActive=" + isActive +
                ", employeeList=" + employeeList +
                ", jobPositionList=" + jobPositionList +
                '}';
    }
}
