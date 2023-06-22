package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.createUser(user.getUsername(), user.getParola()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user")
    public List<User> allUsers(){
        return userService.allUsers();
    }

    @DeleteMapping(value = "/deleteAll")
    public void deleteAll(){
        userService.deleteAll();
    }

    @PostMapping(value="/login")
    public ResponseEntity<Boolean>login(@RequestBody User user){
        return new ResponseEntity<Boolean>(userService.login(user.getUsername(), user.getParola()),HttpStatus.ACCEPTED );
    }

    @DeleteMapping(value = "/user")
    public void deleteByUsername(@RequestParam String username){
        userService.deleteUser(username);
    }

    @PostMapping(value="/follow")
    public void follow(@RequestParam String userFollowed, @RequestParam String userFollower){
        userService.follow(userFollower, userFollowed);
    }
}