package it.proactivity.recruiting.model;

import it.proactivity.recruiting.myEnum.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curriculum that = (Curriculum) o;
        return Objects.equals(id, that.id) && Objects.equals(candidate, that.candidate) &&
                Objects.equals(skill, that.skill) && level == that.level && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, candidate, skill, level, isActive);
    }
}
