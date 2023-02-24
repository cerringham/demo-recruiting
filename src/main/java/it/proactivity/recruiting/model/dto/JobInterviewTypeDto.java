package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class JobInterviewTypeDto {

    private String name;

    public JobInterviewTypeDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JobInterviewTypeDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
