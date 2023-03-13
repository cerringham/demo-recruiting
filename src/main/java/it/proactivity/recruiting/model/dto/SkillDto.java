package it.proactivity.recruiting.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

public class SkillDto {

    private Long id;
    private String name;

    private Boolean isActive;

    private List<Long> candidateSkillListIds;
    private List<Long> skillLevelIds;

    public SkillDto(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public SkillDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SkillDto{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
