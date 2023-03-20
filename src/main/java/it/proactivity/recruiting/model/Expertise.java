package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



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

}
