package com.spring.health.service.impl;

import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.LoginException;
import com.spring.health.model.CurrentSession;
import com.spring.health.model.LoginUUIDKey;
import com.spring.health.model.Patient;
import com.spring.health.repository.PatientRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.PatientAndAdminLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PatientAndAdminLoginServiceImpl implements PatientAndAdminLoginService {

    private final PatientRepository patientRepository;
    private final SessionRepository sessionRepository;

    @Override
    public LoginUUIDKey logIntoAccount(LoginDto loginDTO) throws LoginException {
        LoginUUIDKey loginUUIDKey = new LoginUUIDKey();
        Patient existPatient = patientRepository.findByEmail(loginDTO.getEmail());
        if (existPatient == null) {
            throw new LoginException("Please enter valid email :" + loginDTO.getEmail());
        }
        Optional<CurrentSession> currentUser = sessionRepository.findById(existPatient.getId());
        if (currentUser.isPresent()) {
            if (PatientServiceImpl.bCryptPasswordEncoder.matches(loginDTO.getPassword(), existPatient.getPassword())) {
                loginUUIDKey.setUuid(currentUser.get().getUuid());
                loginUUIDKey.setMsg("Login Successful");
                return loginUUIDKey;
            }
            throw new LoginException("Please enter valid details");
        }
        if (PatientServiceImpl.bCryptPasswordEncoder.matches(loginDTO.getPassword(), existPatient.getPassword())) {
            String key = genarateString();
            CurrentSession currentSession = new CurrentSession(existPatient.getId(), key, LocalDateTime.now());
            if (PatientServiceImpl.bCryptPasswordEncoder.matches("admin", existPatient.getPassword()) && existPatient.getEmail().equals("rabiulnewsinfo@gmail.com")) {
                existPatient.setType("admin");
                currentSession.setUserType("admin");
                currentSession.setUserId(existPatient.getId());
                sessionRepository.save(currentSession);
                patientRepository.save(existPatient);
                loginUUIDKey.setMsg("Login successful as admin with key");
                loginUUIDKey.setUuid(key);
                return loginUUIDKey;
            } else {
                existPatient.setType("patient");
                currentSession.setUserId(existPatient.getId());
                currentSession.setUserType("patient");
            }
            patientRepository.save(existPatient);
            sessionRepository.save(currentSession);
            loginUUIDKey.setMsg("Login Successful as patient with this key");
            loginUUIDKey.setUuid(key);
            return loginUUIDKey;
        } else {
            throw new LoginException("Please enter valid password");
        }
    }

    @Override
    public String logoutFromAccount(String key) throws LoginException {
        return null;
    }


    @Override
    public Boolean checkUserLoginOrNot(String key) throws LoginException {
        CurrentSession currentPatientSession = sessionRepository.findByUuid(key);
        if (currentPatientSession != null) {
            return true;
        } else {
            return false;
        }
    }

    public static String genarateString() {
        String kayValue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random ran = new Random();
        while (sb.length() < 18) {
            int index = (int) (ran.nextFloat() * kayValue.length());
            sb.append(kayValue.charAt(index));
        }
        String str = sb.toString();
        return str;
    }


}
