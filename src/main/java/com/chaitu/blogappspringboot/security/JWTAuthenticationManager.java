package com.chaitu.blogappspringboot.security;

import com.chaitu.blogappspringboot.users.UserEntity;
import com.chaitu.blogappspringboot.users.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTAuthenticationManager implements AuthenticationManager {
    JWTService jwtService;
    UserService userService;

    public JWTAuthenticationManager(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication){
            var jwtauthentication = (JWTAuthentication) authentication;
            var jwt = jwtauthentication.getCredentials();
            Long userId = jwtService.retrieveUserId(jwt);
            UserEntity user = userService.getUser(userId);
            jwtauthentication.user=user;
            jwtauthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtauthentication);
            return jwtauthentication;
        }
        throw new IllegalAccessError("cannot authenticate using non-jwt authentication");

    }
}
