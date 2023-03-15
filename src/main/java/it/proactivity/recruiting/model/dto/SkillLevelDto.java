package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SkillLevelDto {

    private String level;

    private String skillName;

    public SkillLevelDto (String level, String skillName) {
        this.level = level;
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "SkillLevelDto{" +
                ", level='" + level + '\'' +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
