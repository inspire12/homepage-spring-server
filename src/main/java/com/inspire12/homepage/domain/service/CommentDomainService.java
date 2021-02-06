package com.inspire12.homepage.domain.service;

import com.inspire12.homepage.domain.model.Comment;
import com.inspire12.homepage.domain.repository.CommentRepository;
import com.inspire12.homepage.message.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentDomainService {

    private final CommentRepository commentRepository;

    public List<Comment> getComments(Long articleId, int count) {
        return commentRepository.findByArticleId(articleId, PageRequest.of(0, count, Sort.by("id").descending())).getContent();
    }

    public void saveByRequest(CommentRequest request) {
        commentRepository.insertByRequest(request.getArticleId(), request.getUsername(), request.getContent());
    }

    @Transactional
    public void saveReplyByRequest(CommentRequest request) {
        try {
            Comment parentComment = commentRepository.findById(request.getParentId()).get();
            commentRepository.updateReplyOrder(parentComment.getGrpNo(), parentComment.getGrpOrd());
            commentRepository.insertByRequest(request.getArticleId(), request.getUsername(), request.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
