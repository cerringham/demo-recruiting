package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SkillDto {

    private String name;

    private Boolean isActive;

    public SkillDto(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "SkillDto{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
