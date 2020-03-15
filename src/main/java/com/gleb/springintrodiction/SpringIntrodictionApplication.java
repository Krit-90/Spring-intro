package com.gleb.springintrodiction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIntrodictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntrodictionApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("4321"));
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }
}
