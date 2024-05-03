package com.chaitu.blogappspringboot.security;

import com.chaitu.blogappspringboot.users.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication implements Authentication {
    String jwt;
    UserEntity user;
    public JWTAuthentication(String jwt){
        this.jwt=jwt;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserEntity getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user!=null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
