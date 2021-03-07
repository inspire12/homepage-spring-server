package com.inspire12.homepage.service.board;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.service.ArticleDomainService;
import com.inspire12.homepage.domain.service.CommentDomainService;
import com.inspire12.homepage.domain.service.UserDomainService;
import com.inspire12.homepage.dto.article.CommentInfo;
import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.exception.DataNotFoundException;
import com.inspire12.homepage.message.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDomainService commentDomainService;
    private final ArticleDomainService articleDomainService;
    private final UserDomainService userDomainService;

    public List<CommentInfo> getComments(Long articleId, Integer count) {
        return commentDomainService.getComments(articleId, count).stream()
                .map(c -> CommentInfo.create(c,
                        AppUserInfo.create(userDomainService.findById(c.getUserId()).orElseThrow(DataNotFoundException::new))))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveComment(AppUser appUser, CommentRequest request) {
        Article article = articleDomainService.getArticleById(request.getArticleId());
        article.setCommentCount(article.getCommentCount() + 1);
        commentDomainService.saveByRequest(appUser, request);
    }
}
