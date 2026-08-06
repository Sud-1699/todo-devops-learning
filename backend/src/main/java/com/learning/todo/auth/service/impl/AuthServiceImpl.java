package com.learning.todo.auth.service.impl;

import com.learning.todo.auth.dto.AuthResponse;
import com.learning.todo.auth.dto.LoginRequest;
import com.learning.todo.auth.dto.RegisterRequest;
import com.learning.todo.auth.service.AuthService;
import com.learning.todo.common.config.JwtProperties;
import com.learning.todo.common.security.CustomUserDetails;
import com.learning.todo.common.security.JwtService;
import com.learning.todo.role.enums.RoleType;
import com.learning.todo.role.model.Role;
import com.learning.todo.role.repository.RoleRepository;
import com.learning.todo.user.model.User;
import com.learning.todo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already registered.");
        }

        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("ROLE_USER not found."));

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(userRole)
                .enabled(true)
                .accountLocked(false)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow();

        String token = jwtService.generateToken(
                new CustomUserDetails(user)
        );

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(jwtProperties.expiration())
                .build();
    }
}
