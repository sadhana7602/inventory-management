package com.inventory.inventorymanagement.auth.controller;

import com.inventory.inventorymanagement.auth.dto.LoginRequest;
import com.inventory.inventorymanagement.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequest request) {
        String token = service.login(request);
        return Map.of("token", token);
    }
}
