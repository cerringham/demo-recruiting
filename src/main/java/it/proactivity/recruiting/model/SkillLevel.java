package it.proactivity.recruiting.model;

import it.proactivity.recruiting.myEnum.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public String toString() {
        return "SkillLevel{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", level=" + level +
                ", skill=" + skill +
                ", jobPosition=" + jobPosition +
                '}';
    }
}
