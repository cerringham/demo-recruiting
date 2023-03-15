package it.proactivity.recruiting.model;

import jakarta.persistence.*;

@Entity
@Table(name = "application_constant")
public class ApplicationConstant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
}
