package com.spring.health.service;

import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.LoginException;
import com.spring.health.model.LoginUUIDKey;

public interface PatientAndAdminLoginService {
    LoginUUIDKey logIntoAccount(LoginDto loginDTO) throws LoginException;

}
