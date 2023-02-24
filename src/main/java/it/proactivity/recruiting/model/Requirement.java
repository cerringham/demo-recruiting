package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "requirement")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Skill skill;

    @ManyToOne
    private JobPosition jobPosition;

    @Override
    public String toString() {
        return "Requirement{" +
                "id=" + id +
                ", skill=" + skill +
                ", jobPosition=" + jobPosition +
                '}';
    }
}
