package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Repository
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getString("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        List<Comment> commentList = new ArrayList<>();
        for(int j = 0; j < rs.getString("array_id").split(",").length; j++) {
            Comment comment = new Comment();
            comment.setId((rs.getString("array_id").split(",")[j]));
            comment.setName(rs.getString("array_name").split(",")[j]);
            comment.setContent(rs.getString("array_content").split(",")[j]);
            comment.setArticleId((rs.getString("array_article_id").split(",")[j]));
            commentList.add(comment);
        }
        article.setCommentList(commentList);
        return article;
    };

    public List<Article> findAll() {
        String sql = "SELECT DISTINCT a.id,a.name,a.content, " +
                "ARRAY_TO_STRING( " +
                "ARRAY(SELECT c.id " +
                "FROM  comments as c " +
                "WHERE a.id = c.article_id " +
                "ORDER BY c.id DESC" +
                "), ',') as array_id, " +
                "ARRAY_TO_STRING( " +
                "ARRAY(SELECT c.name " +
                "FROM  comments as c " +
                "WHERE a.id = c.article_id " +
                "ORDER BY c.id  DESC" +
                "), ',') as array_name, " +
                "ARRAY_TO_STRING( " +
                "ARRAY(SELECT c.content " +
                "FROM  comments as c " +
                "WHERE a.id = c.article_id " +
                "ORDER BY c.id  DESC " +
                "), ',')  as array_content, "+
                "ARRAY_TO_STRING( " +
                "ARRAY(SELECT c.article_id " +
                "FROM  comments as c " +
                "WHERE a.id = c.article_id " +
                "ORDER BY c.id DESC " +
                "), ',')  as array_article_id "+
                "FROM articles as a " +
                "ORDER BY a.id desc;";


        List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);

        return articleList;
    }

    public void insertArticle(Article article){
        String sql = "INSERT INTO articles (name,content) VALUES(:name,:content)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
        template.update(sql, param);
    }

    public void deleteById(String id){
        String sql =
                "BEGIN;" +
                "DELETE FROM comments WHERE article_id = :id; " +
                "DELETE FROM articles WHERE id = :id; " +
                "COMMIT;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",Integer.valueOf(id));
        template.update(sql, param);
    }

}
