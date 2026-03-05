package com.demo.usermaintenance.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4313467609314410197L;

    @Schema(description = "Egyedi azonosító", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "A név nem lehet üres")
    @Size(min = 2, message = "A név legalább 2 karakter legyen")
    @Schema(description = "A felhasználó teljes neve", example = "Kovács János")
    private String name;

    @NotBlank(message = "Az email nem lehet üres")
    @Email(message = "Érvénytelen email formátum")
    @Schema(description = "A felhasználó email címe", example = "kovacs.janos@example.com")
    private String email;
}
