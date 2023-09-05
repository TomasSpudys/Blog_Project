package com.example.javaspring.controller;

import com.example.javaspring.entity.Comment;
import com.example.javaspring.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.AttributedString;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Optional;


@Controller
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;
    private final CommentService commentService;


    public TopicController(TopicService topicService, CommentService commentService) {
        this.topicService = topicService;
        this.commentService = commentService;
    }


    @GetMapping
    public String listTopics(Model model,@PageableDefault(sort = { "title"}, direction = Sort.Direction.DESC, size = 2, page = 1)
    Pageable pageable) {
        List<Topic> topics = topicService.getAllTopics(pageable);
        model.addAttribute("topic", topics);
        Page<Topic> topicsPage = topicService.findPaginated(pageable);
        model.addAttribute("topics", topicsPage);
        return "topics";
    }


    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("comment", new Comment());
        Topic topic = topicService.getTopic(id);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @PostMapping("/{id}")
    public String addCommentToTopic(@PathVariable Long id, Comment comment, Model model) {
        Topic topic = topicService.getTopic(id);
        comment.setTopic(topic);
        commentService.addCommentToTopic(comment);

        return "redirect:/topics/" + id;


    }

    @GetMapping("/add")
    public String getAddTopicForm(Model model) {
        model.addAttribute("newTopic", new Topic());
        return "addTopic";
    }

    @PostMapping
    public String addNewTopics(Topic newTopic, Model model) {
        Topic savedTopic = topicService.addNewTopic(newTopic);
        topicService.addNewTopic(newTopic);
        return "redirect:/topics";
    }

    @GetMapping("/filter")
    public String filterTopics(@RequestParam String keyword, Model model) {
        List<Topic> topics = topicService.filterTopicsByKeyword(keyword);
        model.addAttribute("topics", topics);
        return "topics";

    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }

}








