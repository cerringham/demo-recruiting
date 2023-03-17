package it.proactivity.recruiting.builder;

import it.proactivity.recruiting.model.dto.SimpleJobPositionDto;

public class SimpleJobPositionDtoBuilder {
    private String status;
    private String companyName;
    private String area;
    private String title;
    private String description;

    private SimpleJobPositionDtoBuilder(String status) {
        this.status = status;
    }

    public static SimpleJobPositionDtoBuilder newBuilder(String status) {
        return new SimpleJobPositionDtoBuilder(status);
    }
    public SimpleJobPositionDtoBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
    public SimpleJobPositionDtoBuilder area(String area) {
        this.area = area;
        return this;
    }
    public SimpleJobPositionDtoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SimpleJobPositionDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public SimpleJobPositionDto build() {
        return new SimpleJobPositionDto(status, companyName, area, title, description);
    }
}
