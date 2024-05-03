package com.chaitu.blogappspringboot.users;

import com.chaitu.blogappspringboot.common.dtos.ErrorResponse;
import com.chaitu.blogappspringboot.security.JWTService;
import com.chaitu.blogappspringboot.users.dto.CreateUserResponse;
import com.chaitu.blogappspringboot.users.dto.CreateUserResquest;
import com.chaitu.blogappspringboot.users.dto.LoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController{
    private UserService userService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;
    UsersController(UserService userService, ModelMapper modelMapper,PasswordEncoder passwordEncoder,JWTService jwtService){
        this.userService=userService;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }
    @PostMapping("")
    public ResponseEntity<CreateUserResponse> signUpUsers(@RequestBody CreateUserResquest resquest){
        var savedUser = userService.createUser(resquest);
        URI userSavedUri = URI.create("/users/"+savedUser.getId());
        var userResponse = modelMapper.map(savedUser,CreateUserResponse.class);
        userResponse.setToken(jwtService.createJwt(userResponse.getId()));
        return ResponseEntity.created(userSavedUri).body(userResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<CreateUserResponse> loginUsers(@RequestBody LoginRequest resquest) throws UserService.InvalidUserException {
        var savedUser = userService.loginUser(resquest.getUsername(),resquest.getPassword());
        var userResponse = modelMapper.map(savedUser,CreateUserResponse.class);
        userResponse.setToken(jwtService.createJwt(userResponse.getId()));

        return ResponseEntity.ok(userResponse);
    }
    @ExceptionHandler({
            UserService.UserNotFoundException.class,
            UserService.InvalidUserException.class
    })
    ResponseEntity<ErrorResponse> handleUserNotFoudException(Exception ex){
        String message;
        HttpStatus status;
        if(ex instanceof UserService.UserNotFoundException){
            message=ex.getMessage();
            status=HttpStatus.NOT_FOUND;
        }
        else if(ex instanceof UserService.InvalidUserException){
            message="Invalid credentials";
            status=HttpStatus.BAD_REQUEST;
        }
        else{
            message="Somthing Internally wrong";
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(ErrorResponse.builder().error(message).build());
    }

}