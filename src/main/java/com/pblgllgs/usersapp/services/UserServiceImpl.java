package com.pblgllgs.usersapp.services;

import com.pblgllgs.usersapp.models.request.UserRequest;
import com.pblgllgs.usersapp.models.entities.User;
import com.pblgllgs.usersapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(UserRequest user, Long id) {
        Optional<User> o = this.findById(id);
        if (o.isPresent()){
            User userDb = o.orElseThrow();
            userDb.setUsername(user.getUsername());
            userDb.setEmail(user.getEmail());
            return Optional.of(this.save(userDb));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
