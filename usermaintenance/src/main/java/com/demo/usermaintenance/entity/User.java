package com.demo.usermaintenance.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Entity class representing a User.
 * Contains fields for the user's basic information such as ID, name, and email.
 */
@Entity(name = "user_sample")
@Data
@Schema(description = "Felhasználó entitás, mely a felhasználó alapvető adatait tartalmazza")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
