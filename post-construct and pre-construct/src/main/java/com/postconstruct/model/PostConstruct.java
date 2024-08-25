package com.postconstruct.model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class PostConstruct implements InitializingBean, DisposableBean {

    private UserRepository userRepository;
    private UserService userService;

    public PostConstruct(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Hello Destruction");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        user.setName("Admin");
        user.setAddress("Dhaka");
        if (userRepository.equals(user)){
            System.out.println("user already save here");
        }
        userRepository.save(user);
    }
}
