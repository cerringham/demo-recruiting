package it.proactivity.recruiting.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class JobPositionDto {

    private String title;

    private String area;

    private String description;

    private String city;

    private String region;

    private String country;

    private Boolean isActive;

    public JobPositionDto(String title, String area, String description, String city, String region, String country,
                          Boolean isActive) {
        this.title = title;
        this.area = area;
        this.description = description;
        this.city = city;
        this.region = region;
        this.country = country;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "JobPositionDto{" +
                "title='" + title + '\'' +
                ", area='" + area + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
