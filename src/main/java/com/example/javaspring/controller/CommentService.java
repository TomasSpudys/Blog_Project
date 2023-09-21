package com.example.javaspring.controller;

import com.example.javaspring.entity.Comment;
import com.example.javaspring.repo.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addCommentToTopic(Comment comment) {commentRepository.save(comment);

    }
    public void deleteComment(Long id) {commentRepository.deleteById(id);
    }
}