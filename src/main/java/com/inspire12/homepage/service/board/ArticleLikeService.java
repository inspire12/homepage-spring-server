package com.inspire12.homepage.service.board;

import com.inspire12.homepage.model.entity.ArticleLike;
import com.inspire12.homepage.model.entity.ArticleLikePk;
import com.inspire12.homepage.repository.ArticleLikeRepository;
import com.inspire12.homepage.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikeService {
    @Autowired
    ArticleLikeRepository articleLikeRepository;

    @Autowired
    ArticleRepository articleRepository;

    public boolean incArticleLike(Long postId, String username){
        articleLikeRepository.save(new ArticleLike(postId, username));
        articleRepository.increaseLikes(postId);
        return true;
    }


    public boolean decArticleLike(Long postId, String username){
        articleLikeRepository.delete(new ArticleLike(postId, username));
        articleRepository.decreaseLikes(postId);
        return true;
    }


}
