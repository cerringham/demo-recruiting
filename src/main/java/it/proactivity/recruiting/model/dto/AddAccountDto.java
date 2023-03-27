package it.proactivity.recruiting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddAccountDto {

    private String name;

    private String surname;

    private String email;

    private String username;

    private String password;
}
