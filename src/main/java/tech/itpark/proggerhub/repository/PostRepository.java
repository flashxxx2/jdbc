package tech.itpark.proggerhub.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tech.itpark.proggerhub.jdbc.JdbcTemplate;
import tech.itpark.proggerhub.model.Post;

import javax.sql.DataSource;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final DataSource ds;

    public List<Post> getAll() {
        return JdbcTemplate.query(
                ds,
                // language=PostgreSQL
                "SELECT id, content, attachment, EXTRACT(EPOCH FROM created) AS created FROM posts",
                rs -> new Post(
                        rs.getLong("id"),
                        null,
                        rs.getString("content"),
                        rs.getString("attachment"),
                        rs.getLong("created")
                )
        );
    }

    public Post postAndReturn(String content, String attachment) {
        return JdbcTemplate.queryInsertAndReturn(
                ds,
                // language=PostgreSQL
                "INSERT INTO posts (content, attachment) VALUES (" + content + ", " + attachment + ") returning id, content, attachment, created",
                rs -> new Post(
                        rs.getLong("id"),
                        null,
                        rs.getString("content"),
                        rs.getString("attachment"),
                        null));
    }

    public Post post(String content, String attachment) {
        return JdbcTemplate.queryInsertAndReturn(
                ds,
                // language=PostgreSQL
                "INSERT INTO posts (content, attachment) VALUES (" + content + ", " + attachment + ")",
                rs -> new Post(
                        rs.getLong("id"),
                        null,
                        rs.getString("content"),
                        rs.getString("attachment"),
                        null));
    }

    public void delete(long postId) {
        JdbcTemplate.queryDelete(
                ds,
                // language=PostgreSQL
                "DELETE FROM posts where id = " + postId + "");
    }

    public Post get(long postId) {
        return JdbcTemplate.queryInsertAndReturn(
                ds,
                // language=PostgreSQL
                "SELECT id, content, attachment, EXTRACT(EPOCH FROM created) AS created FROM posts where id = " + postId + "",
                rs -> new Post(
                        rs.getLong("id"),
                        null,
                        rs.getString("content"),
                        rs.getString("attachment"),
                        rs.getLong("created")
                )
        );
    }
}
