package com.spring.health.service.impl;

import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.LoginException;
import com.spring.health.model.LoginUUIDKey;
import com.spring.health.service.PatientAndAdminLoginService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PatientAndAdminLoginServiceImpl implements PatientAndAdminLoginService {


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
