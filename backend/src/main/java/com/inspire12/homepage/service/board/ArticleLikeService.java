package com.inspire12.homepage.service.board;

import com.inspire12.homepage.model.entity.ArticleLike;
import com.inspire12.homepage.model.entity.ArticleLikePk;
import com.inspire12.homepage.repository.ArticleLikeRepository;
import com.inspire12.homepage.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ArticleLikeService {

    ArticleLikeRepository articleLikeRepository;
    ArticleRepository articleRepository;


    public ArticleLikeService(ArticleLikeRepository articleLikeRepository, ArticleRepository articleRepository) {
        this.articleLikeRepository = articleLikeRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public boolean incArticleLike(Long postId, String username) {
        ArticleLike articleLike = new ArticleLike(postId, username);
        if (articleLikeRepository.existsById(new ArticleLikePk(articleLike.getPostId(), articleLike.getUsername()))) {
            return false;
        }
        articleLikeRepository.save(articleLike);
//        articleRepository.increaseLikes(postId);
        return true;
    }


    public boolean decArticleLike(Long postId, String username) {
        if (articleLikeRepository.existsById(new ArticleLikePk(postId, username))) {
            articleLikeRepository.delete(new ArticleLike(postId, username));
//            articleRepository.decreaseLikes(postId);
            return true;
        }
        return false;
    }


}
