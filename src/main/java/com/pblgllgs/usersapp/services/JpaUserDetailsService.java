package com.pblgllgs.usersapp.services;

import com.pblgllgs.usersapp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.pblgllgs.usersapp.models.entities.User> o = userRepository.getUserByUsername(username);

        if (o.isEmpty()){
            throw new UsernameNotFoundException(String.format("El usuario %s no existe",username));
        }
        com.pblgllgs.usersapp.models.entities.User user = o.orElseThrow();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
