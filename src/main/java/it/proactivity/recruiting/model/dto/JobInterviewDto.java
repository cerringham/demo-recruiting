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

    private String date;

    private String hour;

    private String place;

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

    @Override
    public String toString() {
        return date + " " + hour + " " + place + " " + rating + " " + note + " " + isActive;
    }
}
