package com.chaitu.blogappspringboot.security;

import com.chaitu.blogappspringboot.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    JWTService jwtService;
    UserService userService;
    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter1(){
        return new JWTAuthenticationFilter(new JWTAuthenticationManager(jwtService,userService));
    }
    public WebSecurityConfig(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req)->req
                        .requestMatchers("/articles","/users","/users/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated()

                )
                .addFilterBefore(jwtAuthenticationFilter1(), AnonymousAuthenticationFilter.class)
                .build();

    }

}
