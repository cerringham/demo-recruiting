package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidate_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandidateSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Skill skill;

    @Override
    public String toString() {
        return "CandidateSkill{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", skill=" + skill +
                '}';
    }
}
