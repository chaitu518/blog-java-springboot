package com.chaitu.blogappspringboot.users.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
