package com.chaitu.blogappspringboot.users;

import com.chaitu.blogappspringboot.common.dtos.ErrorResponse;
import com.chaitu.blogappspringboot.users.dto.CreateUserResponse;
import com.chaitu.blogappspringboot.users.dto.CreateUserResquest;
import com.chaitu.blogappspringboot.users.dto.LoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UserService userService;
    private final ModelMapper modelMapper;

    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<CreateUserResponse> signUpUsers(@RequestBody CreateUserResquest resquest){
        var savedUser = userService.createUser(resquest);
        URI userSavedUri = URI.create("/users/"+savedUser.getId());
        return ResponseEntity.created(userSavedUri).body(modelMapper.map(savedUser,CreateUserResponse.class));
    }
    @PostMapping("/login")
    public ResponseEntity<CreateUserResponse> loginUsers(@RequestBody LoginRequest resquest){
        var savedUser = userService.loginUser(resquest.getUsername(),resquest.getPassword());

        return ResponseEntity.ok(modelMapper.map(savedUser,CreateUserResponse.class));
    }
    @ExceptionHandler({
            UserService.UserNotFoundException.class
    })
    ResponseEntity<ErrorResponse> handleUserNotFoudException(Exception ex){
        String message;
        HttpStatus status;
        if(ex instanceof UserService.UserNotFoundException){
            message=ex.getMessage();
            status=HttpStatus.NOT_FOUND;
        }
        else{
            message="Somthing Internally wrong";
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(ErrorResponse.builder().error(message).build());
    }

}
