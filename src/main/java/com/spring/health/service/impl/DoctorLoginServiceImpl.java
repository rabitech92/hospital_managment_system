package com.spring.health.service.impl;

import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.LoginException;
import com.spring.health.model.CurrentSession;
import com.spring.health.model.Doctor;
import com.spring.health.model.LoginUUIDKey;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.DoctorLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DoctorLoginServiceImpl implements DoctorLoginService {

    private final DoctorRepository doctorRepository;
    private final SessionRepository sessionRepository;



    @Override
    public LoginUUIDKey logIntoAccount(LoginDto loginDTO) throws LoginException {
        LoginUUIDKey loginUUIDKey=new LoginUUIDKey();
        Doctor existDoctor = doctorRepository.findByEmail(loginDTO.getEmail());
        if (existDoctor==null){
            throw new LoginException("Please enter valid email "+loginDTO.getEmail());
        }
        if (PatientServiceImpl.bCryptPasswordEncoder.matches(loginDTO.getPassword(), existDoctor.getPassword())){
            if (existDoctor.getValidDoctor()==false){
                throw new LoginException("You don't have permission to login. Please contact Admin for permission.");
            }
            String key =generateRandomString();
            CurrentSession currentSession=new CurrentSession(existDoctor.getId(),key, LocalDateTime.now());
            existDoctor.setType("Doctor");
            currentSession.setUserId(existDoctor.getId());
            currentSession.setUserType("Doctor");
            doctorRepository.save(existDoctor);
            sessionRepository.save(currentSession);
            loginUUIDKey.setMsg("Login successful with valid key");
            loginUUIDKey.setUuid(key);
            return loginUUIDKey;
        }
        else {
            throw new LoginException("Please enter valid password");
        }
    }

    public static String generateRandomString() {
        String keyValue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * keyValue.length());
            salt.append(keyValue.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
