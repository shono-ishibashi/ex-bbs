package com.example.repository;

import com.example.domain.Article;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleRepositoryTest extends TestCase {

    @Autowired
    ArticleRepository repository;

    @Autowired
    NamedParameterJdbcTemplate template;

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs , i) -> {
        Article article = new Article();
        article.setId(String.valueOf(rs.getInt("id")));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        return article;
    };

    @Test
    public void findAll() {
        List<Article> actualList = repository.findAll();
        int actualListsize = actualList.size();
        int expectsize = 2;
        if(actualListsize == 2){
            System.out.println("size OK");
        }

        Article expectArticle1 = new Article();
        expectArticle1.setId("75");
        expectArticle1.setName("KIYOMORI");
        expectArticle1.setContent("jjjjjjjjjjjjjjjjjj");

        Article expectArticle2 = new Article();
        expectArticle2.setId("76");
        expectArticle2.setName("KIYOMORI");
        expectArticle2.setContent("konnni");

        assertEquals("76", actualList.get(0).getId());
        assertEquals("KIYOMORI", actualList.get(0).getName());
        assertEquals("jjjjjjjjjjjjjjjjjj", actualList.get(0).getContent());

        assertEquals("75", actualList.get(1).getId());
        assertEquals("KIYOMORI", actualList.get(1).getName());
        assertEquals("konnni", actualList.get(1).getContent());
    }

    @Test
    public void insertArticle() {
        Article article = new Article();
        article.setName("山田太郎");
        article.setContent("私の名前は山田太郎です");

        repository.insertArticle(article);

        String selectSql = "SELECT * FROM articles WHERE id = 84";
        SqlParameterSource param2 = new MapSqlParameterSource();

        Article actualArticle = template.queryForObject(selectSql, param2,ARTICLE_ROW_MAPPER);

        assertEquals("山田太郎",actualArticle.getName());
        assertEquals("私の名前は山田太郎です",actualArticle.getContent());
        assertEquals("84",actualArticle.getId());

    }

    @Test
    public void deleteById() {
        repository.deleteById("84");

        String selectSql = "SELECT * FROM articles WHERE id = 84";
        SqlParameterSource param2 = new MapSqlParameterSource();

        List <Article> actualArticle = template.query(selectSql, param2,ARTICLE_ROW_MAPPER);

        assertEquals(0,actualArticle.size());
    }



}