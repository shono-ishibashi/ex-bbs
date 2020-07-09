package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
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



import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest extends TestCase {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NamedParameterJdbcTemplate template;


    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs , i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getString("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getString("article_id"));
        return comment;
    };

    @Test
    public void insertComment() {
        Comment comment = new Comment();
        comment.setName("コメント");
        comment.setContent("コメントです");
        comment.setArticleId("83");

        repository.insertComment(comment);


        String expectID = "74";

        String sql = "SELECT * FROM comments WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", Integer.valueOf(expectID));

        Comment actualComment = template.queryForObject(sql, param,COMMENT_ROW_MAPPER);

        assertEquals(expectID, actualComment.getId());
        assertEquals(comment.getContent(),actualComment.getContent());
        assertEquals(comment.getName(), actualComment.getName());
        assertEquals(comment.getArticleId(), actualComment.getArticleId());
    }
}