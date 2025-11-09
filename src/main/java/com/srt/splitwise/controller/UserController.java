package com.srt.splitwise.controller;

import com.srt.splitwise.Models.User;
import com.srt.splitwise.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.removeUserById(id);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }
}
