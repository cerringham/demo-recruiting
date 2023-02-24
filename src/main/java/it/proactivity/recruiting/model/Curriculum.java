package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "curriculum")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Skill skill;

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", skill=" + skill +
                '}';
    }
}
