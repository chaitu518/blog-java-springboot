package com.chaitu.blogappspringboot.users;

import com.chaitu.blogappspringboot.users.dto.CreateUserResquest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserServiceTests {

    @Test
    @Order(1)
    public void can_create_user(){
        var user = new CreateUserResquest("example","1234","example@123.com");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("example",user.getUsername());
    }
}
