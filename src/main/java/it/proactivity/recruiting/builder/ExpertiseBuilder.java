package it.proactivity.recruiting.builder;


import it.proactivity.recruiting.model.Expertise;

public class ExpertiseBuilder {

    private Long id;

    private String name;

    private Boolean isActive;

    private ExpertiseBuilder (Long id) {
        this.id = id;
    }

    public static ExpertiseBuilder newBuilder(Long id) {
        return new ExpertiseBuilder(id);
    }

    public ExpertiseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ExpertiseBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Expertise build() {
        return new Expertise(id, name, isActive);
    }

}
