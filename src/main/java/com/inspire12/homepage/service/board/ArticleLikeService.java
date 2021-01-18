package com.inspire12.homepage.service.board;

import com.inspire12.homepage.domain.model.ArticleLike;
import com.inspire12.homepage.domain.model.ArticleLikeId;
import com.inspire12.homepage.domain.repository.ArticleLikeRepository;
import com.inspire12.homepage.domain.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class ArticleLikeService {

    ArticleLikeRepository articleLikeRepository;
    ArticleRepository articleRepository;


    public ArticleLikeService(ArticleLikeRepository articleLikeRepository, ArticleRepository articleRepository) {
        this.articleLikeRepository = articleLikeRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public boolean increaseArticleLike(Long postId, String username){
        ArticleLike articleLike = new ArticleLike(postId, username, LocalDateTime.now(), LocalDateTime.now(), 0L);
        if (articleLikeRepository.existsById(new ArticleLikeId(articleLike.getPostId(), articleLike.getUsername()))){
            return false;
        }
        articleLikeRepository.save(articleLike);
        articleRepository.increaseLikes(postId);
        return true;
    }

    @Transactional()
    public boolean decreaseArticleLike(Long postId, String username){
        if (articleLikeRepository.existsById(new ArticleLikeId(postId, username))){
            articleLikeRepository.delete(new ArticleLike(postId, username, LocalDateTime.now(), LocalDateTime.now(), 0L));
            articleRepository.decreaseLikes(postId);
            return true;
        }
        return false;
    }


}
