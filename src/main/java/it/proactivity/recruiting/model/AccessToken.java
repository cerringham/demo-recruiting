package it.proactivity.recruiting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Entity
@Table(name = "access_token")
@Getter
@Setter
@NoArgsConstructor
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "token_creation_date_time")
    private LocalDateTime creationTokenDateTime;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    private Account account;

    public AccessToken(String value, LocalDateTime creationTokenDateTime, Boolean isActive, Account account) {
        this.value = value;
        this.creationTokenDateTime = creationTokenDateTime;
        this.isActive = isActive;
        this.account = account;
    }
}
