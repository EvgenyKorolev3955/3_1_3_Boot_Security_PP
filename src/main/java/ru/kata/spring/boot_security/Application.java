package ru.kata.spring.boot_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.entities.Role;
import ru.kata.spring.boot_security.entities.User;

import java.util.HashSet;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
