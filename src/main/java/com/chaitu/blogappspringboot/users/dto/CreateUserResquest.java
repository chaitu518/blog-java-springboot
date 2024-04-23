package com.chaitu.blogappspringboot.users.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserResquest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
}
