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

  public Post post() {
    return JdbcTemplate.queryInsertAndReturn(
            ds,
            // language=PostgreSQL
            "INSERT INTO posts (content, attachment, created) VALUES ('hello', 'world', current_timestamp) returning id, content, attachment, created",
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
