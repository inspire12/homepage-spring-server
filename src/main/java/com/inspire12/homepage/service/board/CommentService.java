package com.inspire12.homepage.service.board;

import com.inspire12.homepage.message.request.CommentRequest;
import com.inspire12.homepage.domain.model.Comment;
import com.inspire12.homepage.dto.message.CommentMsg;
import com.inspire12.homepage.domain.repository.CommentRepository;
import com.inspire12.homepage.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<CommentMsg> getComments(Long articleId) {
        List<Comment> comments = commentRepository.selectCommentByArticleOrder(articleId);
        return convertToCommentMsgs(comments);
    }

    public void saveByRequest(CommentRequest request) {
        commentRepository.insertByRequest(request.getArticleId(), request.getUsername(), request.getContent());
    }

    public void saveReplyByRequest(CommentRequest request) {
        try {
            Comment parentComment = commentRepository.findById(request.getParentId()).get();
            commentRepository.updateReplyOrder(parentComment.getGrpno(), parentComment.getGrpord());
            commentRepository.insertByRequest(request.getArticleId(), request.getUsername(), request.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CommentMsg> convertToCommentMsgs(List<Comment> comments) {
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentMsg commentMsg = CommentMsg.createCommentMsg(comment, userRepository.findByUsername(comment.getUsername()).get());
            commentMsgs.add(commentMsg);
        }
        return commentMsgs;
    }

}
