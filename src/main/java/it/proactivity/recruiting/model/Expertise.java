package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "expertise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private List<Employee> employeeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "expertise_id")
    private List<Candidate> candidateList;

    @Override
    public String toString() {
        return "Expertise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", employeeList=" + employeeList +
                ", candidateList=" + candidateList +
                '}';
    }
}
