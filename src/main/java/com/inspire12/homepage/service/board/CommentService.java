package com.inspire12.homepage.service.board;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.message.CommentMsg;
import com.inspire12.homepage.model.entity.Comment;
import com.inspire12.homepage.repository.CommentRepository;
import com.inspire12.homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    List<CommentMsg> getComments(int articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return convertToCommentMsgs(comments);
    }

    public void saveByRequest(ObjectNode request) {
        int userId = request.get("user_id").asInt();
        int articleId = request.get("article_id").asInt();
        String content = request.get("content").asText();
        Comment comment = createComment(userId, articleId, content);
        try{
            commentRepository.insertByRequest(articleId, userId, content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    private List<CommentMsg> convertToCommentMsgs(List<Comment> comments) {
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentMsg commentMsg = CommentMsg.createCommentMsg(comment, userRepository.getOne(comment.getUserId()));
            commentMsgs.add(commentMsg);
        }
        return commentMsgs;
    }

    public Comment createComment(int userId, int articleId, String content) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setUserId(userId);
        comment.setContent(content);
        return comment;
    }
}
