package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "access_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expiration")
    private LocalTime expiration;

    @ManyToOne
    private Account account;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "name")
    private String name;

    public AccessToken(LocalTime expiration, Account account, Boolean isActive, String name) {
        this.expiration = expiration;
        this.account = account;
        this.isActive = isActive;
        this.name = name;
    }
}
