package it.proactivity.recruiting.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExpertiseDto {

    private Long id;

    private String name;

    private Boolean isActive;

    public ExpertiseDto(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return name + " is active : " + isActive;
    }
}
