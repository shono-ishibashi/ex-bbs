package com.example.controller;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.List;
@RequestMapping("/")
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ServletContext application;

    @ModelAttribute
    private ArticleForm setUpArticleForm(){
        return new ArticleForm();
    }

    @RequestMapping("")
    public String index(){
        List<Article> articleList = articleRepository.findAll();
        application.setAttribute("articleList",articleList);
        return "bbs";
    }

    @RequestMapping("/insert-article")
    public String insertArticle(ArticleForm form) {
        return index();
    }
}
