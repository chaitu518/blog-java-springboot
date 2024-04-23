package com.chaitu.blogappspringboot.articles;

import com.chaitu.blogappspringboot.articles.dto.CreateArticleRequest;
import com.chaitu.blogappspringboot.articles.dto.UpdateArticleRequest;
import com.chaitu.blogappspringboot.users.UserEntity;
import com.chaitu.blogappspringboot.users.UserRepository;
import com.chaitu.blogappspringboot.users.UserService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Locale;

@Service
public class ArticleService {
    ArticleRepository articleRepository;
    UserRepository userRepository;
    public ArticleService(ArticleRepository articleRepository,UserRepository userRepository){
        this.articleRepository=articleRepository;
        this.userRepository=userRepository;
    }
    public Iterable<ArticleEntity> getAllArticles(Long userId){
        return articleRepository.findAll();
    }
    public ArticleEntity getArticleBySlug(String slug){
        ArticleEntity articleEntity =  articleRepository.findBySlug(slug);
        if(articleEntity==null){
            throw new ArticleNotFoundException(slug);
        }
        return articleEntity;
    }
    public ArticleEntity createArticle(CreateArticleRequest createArticleRequest,Long userId){
        var Author = userRepository.findById(userId).orElseThrow(() -> new UserService.UserNotFoundException(userId));

        return articleRepository.save(ArticleEntity.builder()
                        .title(createArticleRequest.getTitle())
                         //need proper slugging
                        .slug(createArticleRequest.getTitle().toLowerCase().replaceAll("\\s","-"))
                        .subtitle(createArticleRequest.getSubtitle())
                        .body((createArticleRequest.getBody()))
                        .author(Author)
                        .build());
    }

    public ArticleEntity updateArticle(Long Id,UpdateArticleRequest updateArticleRequest){
        var article = articleRepository.findById(Id).orElseThrow(() -> new ArticleNotFoundException(Id));
        if(updateArticleRequest.getTitle()!=null){
            article.setTitle(updateArticleRequest.getTitle());
        }
        if(updateArticleRequest.getSubtitle()!=null){
            article.setTitle(updateArticleRequest.getSubtitle());
        }
        if(updateArticleRequest.getBody()!=null){
            article.setTitle(updateArticleRequest.getBody());
        }
        return article;

    }

    static class ArticleNotFoundException extends IllegalArgumentException{
        ArticleNotFoundException(String slug){
            super("Article "+slug+" not found");
        }

        public ArticleNotFoundException(Long Id) {
            super("Article "+Id+" not found");
        }
    }
}
