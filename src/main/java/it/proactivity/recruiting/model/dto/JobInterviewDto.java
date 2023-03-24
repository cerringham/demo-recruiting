package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class JobInterviewDto {

    private Long id;

    private String date;

    private String hour;

    private Long employeeId;

    private Long candidateId;

    private String place;

    private Long jobPositionId;
    private Integer rating;

    private String note;

    private Boolean isActive;

    public JobInterviewDto(String date, String hour, String place, Integer rating, String note, Boolean isActive) {
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.rating = rating;
        this.note = note;
        this.isActive = isActive;
    }

    public JobInterviewDto(String date, String hour, Long employeeId, Long candidateId, String place, Long jobPositionId) {
        this.date = date;
        this.hour = hour;
        this.employeeId = employeeId;
        this.candidateId = candidateId;
        this.place = place;
        this.jobPositionId = jobPositionId;
    }

    public JobInterviewDto(Long id, String date, String hour, Long employeeId, Integer rating, String note, Boolean isActive) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.employeeId = employeeId;
        this.rating = rating;
        this.note = note;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return date + " " + hour + " " + rating + " " + note + " " + isActive;
    }
}
