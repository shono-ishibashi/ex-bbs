package com.example.controller;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.form.ParentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import java.util.ArrayList;
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

    @ModelAttribute
    private ParentForm setUpParentForm(){
        return new ParentForm();
    }

    @RequestMapping(path = "",method = RequestMethod.GET)
    public String index(){
        List<Article> articleList = articleRepository.findAll();
        application.setAttribute("articleList",articleList);
        Integer.valueOf("aaaa");
        return "bbs";
    }

    @RequestMapping(path = "/insert-article",method = RequestMethod.POST)
    public String insertArticle(
            @Validated
            ArticleForm form,
            BindingResult result) {

        if (result.hasErrors()){
            return index();
        }

        Article article = new Article();
        article.setName(form.getName());
        article.setContent(form.getContent());
        articleRepository.insertArticle(article);
        return index();
    }

    @RequestMapping("/insert-comment/{id}")
    public String insertComment(
            @PathVariable ("id") String id,
            @Validated
            ParentForm parentForm,
            BindingResult result
            ){

        if(result.hasErrors()){
            return index();
        }
        Comment comment = new Comment();
        CommentForm commentForm = parentForm.getComments().get(0);
        System.out.println(parentForm);
        comment.setName(commentForm.getName());
        comment.setContent(commentForm.getContent());
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
