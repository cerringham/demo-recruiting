package it.proactivity.recruiting.model.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccessTokenDto {

    private Long id;
    private LocalTime duration;
    private AccountDto accountDto;
    private Boolean isActive;
    private String name;

    @Override
    public String toString() {
        return "AccessTokenDto{" +
                "id=" + id +
                ", duration=" + duration +
                ", accountDto=" + accountDto +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                '}';
    }
}
