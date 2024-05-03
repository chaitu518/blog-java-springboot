package com.chaitu.blogappspringboot.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class JWTServiceTest {
    @Test
    public void verifyJwtToken(){
        JWTService jwtService=new JWTService();
        var jwttoken = jwtService.createJwt(123L);
        Assertions.assertNotNull(jwttoken);
    }
}
