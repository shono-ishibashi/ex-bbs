package com.example.controller;

import com.example.form.ArticleForm;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ArticleController controller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup().build();
    }
    @Test
    void index() throws Exception {
        mockMvc.perform(post("/insert-article"))
                .andExpect(status().isOk());
    }

    @Test
    void insertArticle() throws Exception {
        ArticleForm articleForm = new ArticleForm();
        articleForm.setName("");

        mockMvc.perform((post("/insert-article"))
                .requestAttr("articleForm", articleForm))
                .andExpect(model().hasErrors())
                .andExpect(status().isOk());
    }

    @Test
    void insertComment() {
    }

    @Test
    void deleteById() {
    }
}