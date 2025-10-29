package com.example.user_service.controller;

import com.example.user_service.dto.User;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public String registration(@RequestBody User user) {
        return userService.userRegistration(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // возвращаем токен в теле ответа
        return userService.userLogin(user);
    }

    @PostMapping("/logout")
    public String logout() {
        return userService.userLogOut();
    }

    @GetMapping("/all")
    public List<UserEntity> allUsers(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return userService.showAllUsers();
    }
}
