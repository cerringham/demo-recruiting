package it.proactivity.recruiting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobInterviewInsertionDto {

    private String hour;

    private String date;

    private String place;

    private String jobInterviewStatus;

    private Long employeeId;

    private Long candidateId;

    private Long jobPositionId;
}
