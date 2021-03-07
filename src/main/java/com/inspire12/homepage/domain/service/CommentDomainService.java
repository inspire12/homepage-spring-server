package com.inspire12.homepage.domain.service;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Comment;
import com.inspire12.homepage.domain.repository.CommentRepository;
import com.inspire12.homepage.exception.NotAuthorizeException;
import com.inspire12.homepage.message.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentDomainService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<Comment> getComments(Long articleId, int count) {
        return commentRepository.findByArticleId(articleId, PageRequest.of(0, count, Sort.by("id").descending())).getContent();
    }

    @Transactional
    public void saveByRequest(AppUser appUser, CommentRequest request) {
        if (Objects.isNull(appUser)) {
            throw new NotAuthorizeException();
        }
        Comment comment = new Comment(null,
                request.getArticleId(),
                appUser.getId(),
                0,
                0,
                0,
                request.getContent(),
                0,
                LocalDateTime.now(),
                LocalDateTime.now(),
                0L);
        commentRepository.save(comment);
    }
}
