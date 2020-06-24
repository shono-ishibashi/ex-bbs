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

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs,i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getString("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getString("article_id"));
        return comment;
    };

    public void insertComment(Comment comment) {
        String sql = "INSERT INTO comments (name,content,article_id) VALUES(:name,:content,:articleId) ";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", comment.getName())
                .addValue("content",comment.getContent())
                .addValue("articleId",Integer.valueOf(comment.getArticleId()));
        template.update(sql, param);
    }

    public void deleteByArticleId(String id){
        String sql = "DELETE FROM comments USING articles WHERE articles.id = comments.article_id AND comments.article_id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",Integer.valueOf(id));
        template.update(sql, param);
    }
}
