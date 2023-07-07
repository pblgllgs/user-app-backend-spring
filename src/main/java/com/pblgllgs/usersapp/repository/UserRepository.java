package com.pblgllgs.usersapp.repository;

import com.pblgllgs.usersapp.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username=?1")
    Optional<User> getUserByUsername(String username);
}
