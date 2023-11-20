package com.spring.health.controller;

import com.spring.health.Dto.LoginDto;
import com.spring.health.Dto.RegisterDto;
import com.spring.health.Dto.Response;
import com.spring.health.Dto.UserRegistrationRequest;
import com.spring.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

     private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public Response register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PutMapping("/verify-account")
    public Response verifyAccount(@RequestParam String email,@RequestParam String otp) {
        return userService.verifyAccount(email, otp);
    }

    @PutMapping("/regenerate-otp")
    public Response regenerateOtp(@RequestParam String email) {
        return userService.regenerateOtp(email);
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }


}
