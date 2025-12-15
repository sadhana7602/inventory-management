package com.inventory.inventorymanagement.auth.service;

import com.inventory.inventorymanagement.auth.dto.LoginRequest;

public interface AuthService {
    String login(LoginRequest request);
}
