package com.example.controller;

import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ArticleControllerTest {


    private MockMvc mockMvc;


    @InjectMocks
    ArticleController target;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void index() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void insertArticle異常系() throws Exception {

        ArticleForm form = new ArticleForm();
        form.setName("");
        form.setContent("");



    }

    @Test
    public void insertArticle正常系() throws Exception {

        ArticleForm form = new ArticleForm();
        form.setName("きよもり");
        form.setContent("こんにちは");


    }

    @Test
    void insertComment() {
    }

    @Test
    void deleteById() {
    }
}