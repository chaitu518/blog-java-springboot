package com.chaitu.blogappspringboot.comments;

import com.chaitu.blogappspringboot.articles.ArticleEntity;
import com.chaitu.blogappspringboot.users.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity(name = "comments")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString
public class CommentEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "articleId",nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "authorId",nullable = false)
    private UserEntity author;
}
