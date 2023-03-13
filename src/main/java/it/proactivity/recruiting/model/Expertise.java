package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "expertise")
@Getter
@Setter
@NoArgsConstructor
public class Expertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "expertise_id")
    private Set<Candidate> candidateList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "expertise_id")
    private Set<Employee> employeeList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expertise expertise = (Expertise) o;
        return Objects.equals(id, expertise.id) && Objects.equals(name, expertise.name) &&
                Objects.equals(isActive, expertise.isActive) &&
                Objects.equals(candidateList, expertise.candidateList) &&
                Objects.equals(employeeList, expertise.employeeList);
    }

}
