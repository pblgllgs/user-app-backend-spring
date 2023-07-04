package com.pblgllgs.usersapp.controller;

import com.pblgllgs.usersapp.models.entities.User;
import com.pblgllgs.usersapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        Optional<User> userOptional=  userService.findById(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody() User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @RequestBody User user){
        Optional<User> o = userService.update(user,id);
        if (o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        Optional<User> o = userService.findById(id);
        if (o.isPresent()){
            userService.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
