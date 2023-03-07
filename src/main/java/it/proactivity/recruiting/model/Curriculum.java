package it.proactivity.recruiting.model;

import it.proactivity.recruiting.myEnum.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "curriculum")
@Getter
@Setter
@NoArgsConstructor
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Skill skill;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "is_active")
    private Boolean isActive;

    public Curriculum(Candidate candidate, Skill skill, Level level, Boolean isActive) {
        this.candidate = candidate;
        this.skill = skill;
        this.level = level;
        this.isActive = isActive;
    }

    public Curriculum(Candidate candidate, Skill skill, Boolean isActive) {
        this.candidate = candidate;
        this.skill = skill;
        this.isActive = isActive;
    }
}
