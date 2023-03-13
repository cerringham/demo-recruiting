package it.proactivity.recruiting.model;

import it.proactivity.recruiting.myEnum.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "skill_level")
@Getter
@Setter
@NoArgsConstructor
public class SkillLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @ManyToOne
    private Skill skill;

    @ManyToOne
    private JobPosition jobPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillLevel that = (SkillLevel) o;
        return Objects.equals(id, that.id) && Objects.equals(isActive, that.isActive) && level == that.level &&
                Objects.equals(skill, that.skill) && Objects.equals(jobPosition, that.jobPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, level, skill, jobPosition);
    }
}
