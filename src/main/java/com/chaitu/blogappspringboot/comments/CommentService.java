package com.chaitu.blogappspringboot.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    CommentRepository commentRepository;
    public CommentService(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }
}
