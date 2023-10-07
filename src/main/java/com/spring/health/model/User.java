package com.spring.health.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document("users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseClass {
    private String name;
    private String email;
    private String password;
    private boolean verified;
    private String otp;
    private LocalDateTime otpGeneratedTime;
    private LocalDateTime otpExpiration;
}
