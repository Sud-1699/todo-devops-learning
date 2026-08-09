package com.learning.todo.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@RequiredArgsConstructor
public class SecurityUtils {
    public CustomUserDetails getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null) {
            throw new IllegalStateException(
                    "No authentication found in SecurityContext"
            );
        }

        if (!authentication.isAuthenticated()) {
            throw new IllegalStateException(
                    "User is not authenticated"
            );
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException(
                    "Anonymous user cannot access this resource"
            );
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof CustomUserDetails userDetails)) {
            throw new IllegalStateException(
                    "Unexpected principal type: "
                            + principal.getClass().getName()
            );
        }

        return userDetails;
    }

}
