package com.inventory.inventorymanagement.user.service;

import com.inventory.inventorymanagement.user.dto.RegisterRequest;
import com.inventory.inventorymanagement.user.entity.Role;
import com.inventory.inventorymanagement.user.entity.User;
import com.inventory.inventorymanagement.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository,
                           PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));

        return repository.save(user);
    }
}
