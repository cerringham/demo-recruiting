package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.NewAndUrgentJobPositionDto;

public class NewAndUrgentJobPositionDtoBuilder {

    private final String status;

    private String companyName;

    private String area;

    private String title;

    private String description;

    private NewAndUrgentJobPositionDtoBuilder(String status) {
        this.status = status;
    }

    public static NewAndUrgentJobPositionDtoBuilder newBuilder(String status) {
        return new NewAndUrgentJobPositionDtoBuilder(status);
    }

    public NewAndUrgentJobPositionDtoBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public NewAndUrgentJobPositionDtoBuilder area(String area) {
        this.area = area;
        return this;
    }

    public NewAndUrgentJobPositionDtoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public NewAndUrgentJobPositionDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public NewAndUrgentJobPositionDto build() {
        return new NewAndUrgentJobPositionDto(status, companyName, area, title, description);
    }
}
