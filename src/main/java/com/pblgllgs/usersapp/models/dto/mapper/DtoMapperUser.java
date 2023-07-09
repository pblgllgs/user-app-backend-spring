package com.pblgllgs.usersapp.models.dto.mapper;

import com.pblgllgs.usersapp.models.dto.UserDto;
import com.pblgllgs.usersapp.models.entities.User;

public class DtoMapperUser {

    private User user;

    private DtoMapperUser() {
    }

    public static DtoMapperUser builder(){
        return new DtoMapperUser();
    }

    public DtoMapperUser setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build(){
        if(user == null){
            throw new RuntimeException("Debe pasar el entity user!");
        }
        boolean isAdmin = user.getRoles().stream().anyMatch( r -> "ROLE_ADMIN".equals(r.getName()));
        return new UserDto(this.user.getId(), this.user.getUsername(), this.user.getEmail(), isAdmin);
    }

}
