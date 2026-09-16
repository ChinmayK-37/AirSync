package com.cp.airsync.controllers;

import com.cp.airsync.entity.User;
import com.cp.airsync.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User savedUser = userService.save(user);

        return new ResponseEntity<>(
                savedUser,
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {

        User user = userService.findUserById(id);

        return new ResponseEntity<>(
                user,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<List<User>> findUsers() {

        List<User> users = userService.listAll();

        return new ResponseEntity<>(
                users,
                HttpStatus.OK
        );
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}