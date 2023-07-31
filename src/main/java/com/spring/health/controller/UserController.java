package com.spring.health.controller;

import com.spring.health.model.Patient;
import com.spring.health.model.User;
import com.spring.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }
    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }
    @PutMapping("/update/{id}")
    public User updatePatient(@RequestBody User user, @PathVariable Long id) {
        return userService.update(user,id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
