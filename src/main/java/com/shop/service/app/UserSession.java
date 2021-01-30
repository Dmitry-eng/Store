package com.shop.service.app;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserSession {
    private UserSession() {
    }

    public static String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
