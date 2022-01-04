package com.pap.zoo.controller;

import com.pap.zoo.entity.User;
import com.pap.zoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Pair<Boolean, String> addUser(@RequestBody User user) { return userService.saveUser(user); }

    @GetMapping("/list")
    public List<User> findAllUsers() { return userService.getUsers(); }

    @GetMapping("/userById/{id}")
    public User findAnimalById(@PathVariable int id) { return userService.getUserById(id); }

    @GetMapping("/userIsValid/{login}/{pass}")
    public Pair<Boolean, String> findValidZooKeeper(@PathVariable String login, @PathVariable String pass) { return userService.isValidUser(login, pass); }

    @PutMapping("/update")///check
    public Pair<Boolean, String> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public Pair<Boolean, String> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
