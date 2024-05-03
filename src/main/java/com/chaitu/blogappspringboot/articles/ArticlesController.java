package com.chaitu.blogappspringboot.articles;

import com.chaitu.blogappspringboot.users.UserEntity;
import com.chaitu.blogappspringboot.users.UserService;
import com.chaitu.blogappspringboot.users.dto.CreateUserResponse;
import com.chaitu.blogappspringboot.users.dto.CreateUserResquest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    @GetMapping("")
    public String getArticles(){
        return "articles";
    }
    @PostMapping("")
    public String createArticles(@AuthenticationPrincipal UserEntity user) {
        String mes="";
        if(user==null){
            mes="null";
        }
        return "article get created by "+mes;
    }

}