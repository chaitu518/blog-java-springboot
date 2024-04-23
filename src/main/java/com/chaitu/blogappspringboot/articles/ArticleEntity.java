package com.chaitu.blogappspringboot.articles;

import com.chaitu.blogappspringboot.users.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column(unique = true)
    private String slug;

    @Nullable
    private String subtitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "authorId",nullable = false)
    private UserEntity author;
}
