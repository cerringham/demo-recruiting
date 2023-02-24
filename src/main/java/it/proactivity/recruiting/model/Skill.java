package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private List<Curriculum> curriculumList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private List<Requirement> requirementList;

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", curriculumList=" + curriculumList +
                ", requirementList=" + requirementList +
                '}';
    }
}
