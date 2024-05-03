package com.chaitu.blogappspringboot.users.dto;

import lombok.Data;

@Data
public class CreateUserResponse {

    private Long id;

    private String username;

    private String email;

    private String bio;

    private String image;

    private String token;
}
