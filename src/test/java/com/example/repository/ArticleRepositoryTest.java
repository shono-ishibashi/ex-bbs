package com.example.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void findAll() {

        System.out.println(articleRepository.findAll());
    }

    @org.junit.jupiter.api.Test
    void insertArticle() {
    }

    @org.junit.jupiter.api.Test
    void deleteById() {
    }
}