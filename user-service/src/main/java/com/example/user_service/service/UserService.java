package com.example.user_service.service;

import com.example.user_service.dto.User;
import com.example.user_service.entity.UserEntity;

import java.util.List;

public interface UserService {
    String userRegistration(User user);
    String userLogin(User user);
    List<UserEntity> showAllUsers();
    String userLogOut(); // минимально — клиент просто удалит токен
}
