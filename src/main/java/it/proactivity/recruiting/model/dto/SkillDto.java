package it.proactivity.recruiting.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SkillDto {

    private String name;

    private Boolean isActive;

    @Override
    public String toString() {
        return "SkillDto{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
