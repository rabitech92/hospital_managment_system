package com.spring.health.service.impl;

import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.LoginException;
import com.spring.health.model.CurrentSession;
import com.spring.health.model.LoginUUIDKey;
import com.spring.health.repository.PatientRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.PatientAndAdminLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PatientAndAdminLoginServiceImpl implements PatientAndAdminLoginService {

    private final PatientRepository patientRepository;
    private final SessionRepository sessionRepository;


    @Override
    public Boolean checkUserLoginOrNot(String key) throws LoginException {
        CurrentSession currentPatientSession = sessionRepository.findByUuid(key);
        if(currentPatientSession != null) {
            return true;
        }else {
            return false;
        }
    }
    public static String genarateString(){
        String kayValue="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb =new StringBuilder();
        Random ran=new Random();
        while (sb.length()<18){
            int index = (int) (ran.nextFloat() * kayValue.length());
            sb.append(kayValue.charAt(index));
        }
        String saltStr=sb.toString();
        return saltStr;
    }

    @Override
    public LoginUUIDKey logIntoAccount(LoginDto loginDTO) throws LoginException {
        return null;
    }
}
