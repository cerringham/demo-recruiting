package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skill")
@Getter
@Setter
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private List<Curriculum> candidateSkillList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private List<SkillLevel> skillList;

    public Skill(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && Objects.equals(name, skill.name) &&
                Objects.equals(isActive, skill.isActive) &&
                Objects.equals(candidateSkillList, skill.candidateSkillList) && Objects.equals(skillList, skill.skillList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive, candidateSkillList, skillList);
    }
}
