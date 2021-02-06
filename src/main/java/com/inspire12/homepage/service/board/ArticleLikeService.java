package com.inspire12.homepage.service.board;

import com.inspire12.homepage.common.LikeType;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.UserLike;
import com.inspire12.homepage.domain.model.UserLikeId;
import com.inspire12.homepage.domain.repository.UserLikeRepository;
import com.inspire12.homepage.domain.repository.ArticleRepository;
import com.inspire12.homepage.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {
    UserLikeRepository userLikeRepository;
    ArticleRepository articleRepository;
//   TODO  ArticleDomainService articleDomainService;

    @Transactional
    public boolean increaseArticleLike(Long postId, Long userId) {
        UserLike articleLike = userLikeRepository.findById(new UserLikeId(postId, userId, LikeType.ARTICLE))
                .orElseGet(() -> new UserLike(postId, userId, LikeType.ARTICLE, true, LocalDateTime.now(), LocalDateTime.now(), 0L));
        articleLike.setLiked(true);

        Article article = articleRepository.findById(postId).orElseThrow(CommonException::new);
        article.setLikes(article.getLikes() + 1);
        return true;
    }

    @Transactional
    public boolean decreaseArticleLike(Long postId, Long userId) {
        UserLike articleLike = userLikeRepository.findById(new UserLikeId(postId, userId, LikeType.ARTICLE))
                .orElseThrow(CommonException::new);
        if (articleLike.isLiked()) {
            articleLike.setLiked(false);
            Article article = articleRepository.findById(postId).orElseThrow(CommonException::new);
            article.setLikes(article.getLikes() - 1);
            return true;
        }
        return false;
    }
}
