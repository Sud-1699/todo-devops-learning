package com.learning.todo.auth.service;

import com.learning.todo.auth.dto.AuthResponse;
import com.learning.todo.auth.dto.LoginRequest;
import com.learning.todo.auth.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
