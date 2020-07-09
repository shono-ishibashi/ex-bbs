package com.example.repository;

import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public void insertComment(Comment comment) {
        String sql = "INSERT INTO comments (name,content,article_id) VALUES(:name,:content,:articleId) ";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", comment.getName())
                .addValue("content",comment.getContent())
                .addValue("articleId",Integer.valueOf(comment.getArticleId()));
        template.update(sql, param);
    }
}
