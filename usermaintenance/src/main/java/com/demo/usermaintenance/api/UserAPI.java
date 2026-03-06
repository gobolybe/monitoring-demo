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

/**
 * Interface for managing User operations through API endpoints. Supports listing,
 * creating, and deleting users in the system. This interface defines the contract
 * for user-related REST operations.
 */
@Tag(name = "Felhasználó Kezelés", description = "Felhasználók kezelésére szolgáló API végpontok")
public interface UserAPI {
    /**
     * Retrieves a list of all users in the system, optionally filtered by name.
     *
     * @param name The name or partial name to filter users by. If null or empty, all users are returned.
     * @return A list of UserDTO objects representing the users that match the filter criteria.
     */
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

    /**
     * Creates a new user in the system with the provided user information.
     *
     * @param user A {@code UserDTO} object containing the details of the user to be created,
     *             such as name and email. The input is validated to ensure it meets the
     *             required constraints (e.g., name length, valid email format).
     * @return A {@code UserDTO} object representing the newly created user, including its
     * generated unique identifier and other details.
     */
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

    /**
     * Deletes a user from the system based on their unique identifier (ID).
     *
     * @param id The unique identifier of the user to be deleted.
     */
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
