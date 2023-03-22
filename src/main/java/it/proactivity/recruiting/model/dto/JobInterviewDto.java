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
    private Integer rating;

    private String note;

    private Boolean isActive;

    public JobInterviewDto(String date, String hour, Long employeeId, Integer rating, String note, Boolean isActive) {
        this.date = date;
        this.hour = hour;
        this.employeeId = employeeId;
        this.rating = rating;
        this.note = note;
        this.isActive = isActive;
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
