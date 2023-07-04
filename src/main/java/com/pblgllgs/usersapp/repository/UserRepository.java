package com.pblgllgs.usersapp.repository;

import com.pblgllgs.usersapp.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
