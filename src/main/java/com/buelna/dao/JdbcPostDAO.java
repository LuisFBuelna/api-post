package com.buelna.dao;

import com.buelna.entities.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcPostDAO implements PostDAO<Post> {

    private static final Logger log = LoggerFactory.getLogger(PostDAO.class);

    private JdbcTemplate jdbcTemplate;

    public JdbcPostDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Post> rowMapper = (rs, rowNum) -> {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setUserId(rs.getInt("user_id"));
        post.setTitle(rs.getString("title"));
        post.setBody(rs.getString("body"));
        return post;
    };

    @Override
    public List<Post> getAllPost() {
        String sql = "SELECT id, user_id, title, body FROM post";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void savePost(List<Post> posts) {
        String sql = "INSERT INTO post(id, user_id, title, body) values (?, ?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Post post = posts.get(i);
                ps.setInt(1, post.getId());
                ps.setInt(2, post.getUserId());
                ps.setString(3, post.getTitle());
                ps.setString(4, post.getBody());
            }

            @Override
            public int getBatchSize() {
                return posts.size();
            }
        });
    }

    @Override
    public void deleteAllPost() {
        String sql = "DELETE FROM post";
        jdbcTemplate.update(sql);
    }


}
