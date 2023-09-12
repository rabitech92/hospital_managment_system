//package com.spring.health.service;
//
//import com.spring.health.model.User;
//import com.spring.health.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService  implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userRepository.findByEmail(username);
//        if (user !=null){
//            String email=user.getEmail();
//            String password =user.getPassword();
//        }else {
//            return (UserDetails) new UsernameNotFoundException("Invaild Email or Password");
//        }
//        return (UserDetails) user;
//
//    }
//}
