package com.eazybytes.DemoUsersApplication.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

//        return Optional.of("Ruby"); //to return a static name
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty(); // No logged-in user
        }

        // Get the logged-in username
        String username = authentication.getName();
        return Optional.of(username);
    }
    }

