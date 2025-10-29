package com.example.user_service.service;
import com.example.user_service.dto.User;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String userRegistration(User user) {
        if (userRepository.existsByUserLogin(user.getUserLogin())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This login already taken");
        }
        UserEntity entity = new UserEntity(user.getUserLogin(), user.getUserPassword());
        userRepository.save(entity);
        return "User saved";
    }

    @Override
    public String userLogin(User user) {
        Optional<UserEntity> userEntity = userRepository.findByUserLogin(user.getUserLogin());

        if (userEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        if (!userEntity.get().getUserPassword().equals(user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }

        // Генерируем токен
        String token = jwtUtil.generateToken(userEntity.get().getUserLogin(), userEntity.get().getUserID());
        return token;
    }

    @Override
    public List<UserEntity> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String userLogOut() {
        // Если используешь stateless JWT — логаут делается на клиенте (удалить токен).
        // Можно сделать blacklist, но это за рамками минимального примера.
        return "Logged out (client should discard token)";
    }
}
