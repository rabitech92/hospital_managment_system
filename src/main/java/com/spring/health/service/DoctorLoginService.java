package com.spring.health.service;

import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.LoginException;
import com.spring.health.model.LoginUUIDKey;

public interface DoctorLoginService {
    LoginUUIDKey logIntoAccount(LoginDto loginDTO) throws LoginException;
    LoginUUIDKey logOutAccount(LoginDto loginDTO) throws LoginException;
    Boolean checkUserLoginOrNot(String key) throws LoginException;


}
