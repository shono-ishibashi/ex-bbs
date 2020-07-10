package com.example.controller;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.List;
@RequestMapping("/")
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ServletContext application;

    @ModelAttribute
    private ArticleForm setUpArticleForm(){
        return new ArticleForm();
    }

    @ModelAttribute
    private CommentForm setUpCommentForm(){
        return new CommentForm();
    }

    @RequestMapping("")
    public String index(){
        List<Article> articleList = articleRepository.findAll();
        application.setAttribute("articleList",articleList);
        return "bbs";
    }

    @RequestMapping("/insert-article")
    public String insertArticle(
            @Validated
            ArticleForm form,
            BindingResult result) {
    	System.out.println("ああああああああああああああ");
        if (result.hasErrors()){
            return index();
        }
<<<<<<< HEAD
=======

>>>>>>> d970d5302104ff22be6729d794a51ebb8794ba5d
        System.out.println("いいいいい");

        System.out.println("aaaaaa");

<<<<<<< HEAD
=======

>>>>>>> d970d5302104ff22be6729d794a51ebb8794ba5d
        Article article = new Article();
        article.setName(form.getName());
        article.setContent(form.getContent());
        
        System.out.println(article);
        
        articleRepository.insertArticle(article);
        return index();
    }


    @RequestMapping("/insert-comment/{id}")
    public String insertComment(
            @PathVariable ("id") String id,
            @Validated
            CommentForm form,
            BindingResult result){

        if(result.hasErrors()){
            return index();
        }
        Comment comment = new Comment();
        comment.setName(form.getName());
        comment.setContent(form.getContent());
        comment.setArticleId(id);
        commentRepository.insertComment(comment);
        return index();
    }

    @RequestMapping("/delete-article/{id}")
    public String deleteById(@PathVariable ("id") String id){
        articleRepository.deleteById(id);
        return index();
    }
}
