package it.proactivity.recruiting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AddAccountDto extends LoginDto {

    private String name;

    private String surname;

    private String email;

    public AddAccountDto(String name, String surname, String email, String username, String password) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
