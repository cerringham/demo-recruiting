package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "skill_list")
@Getter
@Setter
@NoArgsConstructor
public class SkillList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    private Skill skill;

    @ManyToOne
    private JobPosition jobPosition;
}
