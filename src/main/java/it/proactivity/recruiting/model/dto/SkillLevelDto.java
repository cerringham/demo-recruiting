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

    private Boolean isActive;

    private String level;

    private String skillName;

    public SkillLevelDto (Boolean isActive, String level, String skillName) {
        this.isActive = isActive;
        this.level = level;
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "SkillLevelDto{" +
                "isActive=" + isActive +
                ", level='" + level + '\'' +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
