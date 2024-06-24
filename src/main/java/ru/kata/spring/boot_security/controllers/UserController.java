package ru.kata.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.entities.User;
import ru.kata.spring.boot_security.security.UserDetailsImpl;
import ru.kata.spring.boot_security.services.UserService;


@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user")
    public String getUserPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "user";
    }


    @GetMapping("/admin")
    public String getAll(ModelMap model) {

        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping(value = "admin/new")
    public String getNewUserForm(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("msg", "Создать нового пользователя");
        return "new";
    }

    @PostMapping("/admin")
    public String saveUser(@ModelAttribute("user") User user, Model model) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!userService.saveUser(user)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "admin/new";
        }
        return "redirect:/admin";
    }

    @GetMapping("admin/edit")
    public String getEditUserForm(@RequestParam(value = "id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("msg", "Изменить существующего пользователя");
        return "edit";
    }

    @PutMapping("/admin")
    public String updateUser(@ModelAttribute("user") User user, Model model) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!userService.updateUser(user)) {
            model.addAttribute("updateUserError", "Не удалось обновить пользователя");
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/admin")
    public String delete(@RequestParam(value = "id") long id, Model model) {

        if (!userService.deleteById(id)) {
            model.addAttribute("deleteUserError", "Не удалось удалить пользователя");
        }
        return "redirect:/admin";
    }


}
