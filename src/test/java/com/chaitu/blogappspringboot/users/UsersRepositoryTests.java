package com.chaitu.blogappspringboot.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")

public class UsersRepositoryTests {
    @Autowired
    UserRepository userRepository;
    @Test
    @Order(1)
    public void can_create_users(){
        var user = UserEntity.builder()
                .username("Nagasri")
                .email("example@gmail.com").build();
        userRepository.save(user);
    }
    @Test
    @Order(2)
    public void can_find_user(){
        var user = UserEntity.builder()
                .username("Nagasri")
                .email("example@gmail.com").build();
        userRepository.save(user);
        int size=userRepository.findAll().size();
        Assertions.assertEquals(1,size);
    }


}
