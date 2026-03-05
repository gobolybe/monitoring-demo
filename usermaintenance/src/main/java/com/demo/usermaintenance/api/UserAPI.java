package com.demo.usermaintenance.api;

import com.demo.usermaintenance.entity.User;
import com.demo.usermaintenance.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Felhasználó Kezelés", description = "Felhasználók kezelésére szolgáló API végpontok")
public interface UserAPI {
    @Operation(
            summary = "Összes felhasználó lekérése",
            description = "Visszaadja a rendszerben lévő összes felhasználót, szűrhető név alapján.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
            }
    )
    @GetMapping("/list")
    List<UserDTO> getAllUsers(
            @Parameter(description = "A keresendő felhasználó neve vagy névrészlete")
            @RequestParam String name);

    @Operation(
            summary = "Új felhasználó létrehozása",
            description = "Létrehoz egy új felhasználót a megadott adatokkal.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sikeres mentés",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Érvénytelen adatok", content = @Content)
            }
    )
    @PostMapping
    UserDTO createUser(@Valid @RequestBody UserDTO user);

    @Operation(
            summary = "Felhasználó törlése",
            description = "Törli a felhasználót az egyedi azonosítója (ID) alapján.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sikeres törlés"),
                    @ApiResponse(responseCode = "404", description = "Felhasználó nem található", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    void deleteUser(
            @Parameter(description = "A törlendő felhasználó egyedi azonosítója")
            @PathVariable Long id);
}
