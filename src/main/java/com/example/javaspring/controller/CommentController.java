package com.example.javaspring.controller;


import com.example.javaspring.entity.Comment;
import com.example.javaspring.entity.Topic;
import com.example.javaspring.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final TopicService topicService;
    private final CommentService commentService;

    public CommentController(TopicService topicService, CommentService commentService) {
        this.topicService = topicService;
        this.commentService = commentService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public String addCommentToTopic(@AuthenticationPrincipal User user, Long id, Comment comment, Model model) {
        Topic topic = topicService.getTopic(id);
        comment.setCreatedBy(user);
        comment.setTopic(topic);
        commentService.addCommentToTopic(comment);

        return "redirect:/topics/" + id;
    }
}
