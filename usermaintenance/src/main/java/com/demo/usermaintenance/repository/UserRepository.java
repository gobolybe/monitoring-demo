package com.demo.usermaintenance.repository;

import com.demo.usermaintenance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@code User} entities. Provides methods for
 * performing database operations such as querying, saving, updating, and deleting users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves a list of users whose names contain the specified substring, ignoring case.
     *
     * @param name the substring to search for within user names. The search is case-insensitive.
     * @return a list of {@code User} entities that match the search criteria.
     */
    List<User> findByNameContainingIgnoreCase(String name);
}
