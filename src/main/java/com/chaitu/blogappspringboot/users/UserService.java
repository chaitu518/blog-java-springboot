package com.chaitu.blogappspringboot.users;

import com.chaitu.blogappspringboot.users.dto.CreateUserResquest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(CreateUserResquest userResquest){
        UserEntity user = UserEntity.builder()
                .username(userResquest.getUsername())
                .password(passwordEncoder.encode(userResquest.getPassword()))
                .email(userResquest.getEmail())
                .build();
        return userRepository.save(user);
    }
    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
    public UserEntity loginUser(String username,String password) throws InvalidUserException {
        var user =  userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        var passmatch = passwordEncoder.matches(password,user.getPassword());
        if(!passmatch){
            throw new InvalidUserException();
        }

        return user;
    }
   public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username){
            super("user "+username+" not found");
        }
        public UserNotFoundException(Long userId){
            super("user "+userId+" not found");
        }
    }
    public static class InvalidUserException extends IllegalAccessException{
        public InvalidUserException(){
            super("Invalid username and password");
        }

    }
}
