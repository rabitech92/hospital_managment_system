package com.spring.health.practice.config;

import com.spring.health.practice.classConcept.A;
import com.spring.health.practice.classConcept.B;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public A a(){
        return new A();
    }

    @Bean
    public B b()
    {
        return new B();
    }
}
