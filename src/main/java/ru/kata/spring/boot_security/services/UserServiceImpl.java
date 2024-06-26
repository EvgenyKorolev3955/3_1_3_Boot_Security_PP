package ru.kata.spring.boot_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.models.Role;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.repositories.RoleRepository;
import ru.kata.spring.boot_security.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    public boolean saveUser(User user) {

        Optional<User> userFromDB = userRepository.findById(user.getId());

        if (userFromDB.isPresent()) {
            return false;
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(1L).get());
        user.setRoles(roles);
        userRepository.save(user);

        return true;

    }

    @Override
    public boolean updateUser(User user) {

        Optional<User> userFromDB = userRepository.findById(user.getId());
        if (userFromDB.isPresent()) {

            User updatedUser = userRepository.findById(user.getId()).orElse(new User());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEmail(user.getEmail());
            userRepository.save(updatedUser);

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {

            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
