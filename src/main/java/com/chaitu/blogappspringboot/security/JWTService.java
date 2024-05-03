package com.chaitu.blogappspringboot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String JWT_KEY="shdf332423renfojshdfwfjjjj2j432423jkdj";
    Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
    public String createJwt(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                .sign(algorithm);
    }
    public Long retrieveUserId(String jwtString){
        var decoder = JWT.decode(jwtString);
        var userId = Long.valueOf(decoder.getSubject());
        return userId;
    }
}
