package ru.kata.spring.boot_security.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    @Transactional(readOnly = true)
    List<User> findAll();

    @Transactional(readOnly = true)
    User findById(Long Id);

    @Transactional(readOnly = true)
    Optional<User> findByUsername(String username);

    @Transactional
    boolean saveUser(User user);

    @Transactional
    boolean updateUser(User user);

    @Transactional
    boolean deleteById(Long id);

}
