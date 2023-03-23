package it.proactivity.recruiting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobInterviewUpdateDto {

    private Long jobInterviewId;

    private String date;

    private String hour;

    private String rating;

    private String note;

    private Long employeeId;
}
