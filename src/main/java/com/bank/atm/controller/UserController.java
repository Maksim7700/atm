package com.bank.atm.controller;

import com.bank.atm.model.User;
import com.bank.atm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/me")
    public User getUser() {
        
        return userService.getCurrentUser();
    }
    
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        
        return userService.findUserById(id);
    }
    
    @GetMapping("/users")
    public List<User> getUsers() {
        
        return userService.findAll();
    }
    
    @PutMapping("/user")
    public User saveOrUpdate(@RequestBody User user) {
        
        userService.saveOrUpdateUser(user);
        return user;
    }
    
    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        
        userService.saveOrUpdateUser(user);
        return user;
    }
    
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable ("id") Long id) {
    
        User user = userService.findUserById(id);
        userService.deleteUser(user);
        return "Removed";
    }
    
    
    
    
    
    
}
