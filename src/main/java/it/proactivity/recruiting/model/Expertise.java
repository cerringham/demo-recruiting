package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;


@Entity
@Table(name = "expertise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Expertise(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Expertise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", candidateList=" + candidateList +
                ", employeeList=" + employeeList +
                '}';
    }
}
