package com.inventory.inventorymanagement.user.service;

import com.inventory.inventorymanagement.user.dto.RegisterRequest;
import com.inventory.inventorymanagement.user.entity.User;

public interface UserService {
    User register(RegisterRequest request);
}
