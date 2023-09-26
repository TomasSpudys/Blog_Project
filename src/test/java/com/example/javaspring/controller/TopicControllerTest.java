package com.example.javaspring.controller;


import com.example.javaspring.entity.Topic;
import com.example.javaspring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// test slice
@WebMvcTest(TopicController.class)
class TopicControllerTest {

    @MockBean
    private TopicService topicService;

    @MockBean
    private UserService userService;
    @MockBean
    private CommentService commentService;


    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = "USER")
    @Test
    void getTopic_topicExists_returnsTopicPage() throws Exception {
        //given
        long givenTopicId = 1L;
        Topic givenTopicFoundInDb = new Topic();
        givenTopicFoundInDb.setId(givenTopicId);
        givenTopicFoundInDb.setHeader("Some very special topic header");
        givenTopicFoundInDb.setTitle("Some very special topic title");

        when(topicService.getTopic(givenTopicId)).thenReturn(givenTopicFoundInDb);

        //when-then
        mockMvc.perform(get("/topics/view/%d".formatted(givenTopicId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic"))
                .andExpect(model().attributeExists("topic"))
                .andExpect(content().string(containsString("Some very special topic title")))
                .andExpect(content().string(containsString("Some very special topic header")));
//  xpath matcher is supposed to verify xml  (html is extension of it, containing lots of new things, not allowed by xpath)
//  ... you're gonna use it fot verifying SOAP services
//                .andExpect(xpath("/html/body/main/div/h3").string("Some very special topic title"))
//                .andExpect(xpath("/html/body/main/div/section").string("Some very special topic header"));
    }

    @Test
    void addNewTopic_userNotLoggedIn_redirectsToLogin() throws Exception {
        //given
        long givenTopicId = 1L;
        Topic userSubmittedNewTopic = new Topic();
        userSubmittedNewTopic.setId(givenTopicId);
        userSubmittedNewTopic.setHeader("Some very special topic header");
        userSubmittedNewTopic.setTitle("Some very special topic title");

        //when-then
        mockMvc.perform(post("/topics/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Some very special topic title")
                        .param("header", "Some very special topic header")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }


}
