package com.chaitu.blogappspringboot.users;

import com.chaitu.blogappspringboot.users.dto.CreateUserResquest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public UserEntity createUser(CreateUserResquest userResquest){
        UserEntity user = UserEntity.builder()
                .username(userResquest.getUsername())
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
    public UserEntity loginUser(String username,String password){
        var user =  userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

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
}
