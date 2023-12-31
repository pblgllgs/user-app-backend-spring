package com.pblgllgs.usersapp.services;

import com.pblgllgs.usersapp.models.dto.UserDto;
import com.pblgllgs.usersapp.models.request.UserRequest;
import com.pblgllgs.usersapp.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);

    UserDto save(User user);
    Optional<UserDto> update(UserRequest user, Long id);

    void remove(Long id);
}
