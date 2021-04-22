package tech.itpark.proggerhub.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tech.itpark.proggerhub.dto.PostDto;
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

    public Post postAndReturn(PostDto postDto) {
        String sql = String.format(
                "INSERT INTO posts (content, attachment) VALUES ('%s', '%s') returning id, content, attachment, EXTRACT(EPOCH FROM created) AS created",
                postDto.getContent(),
                postDto.getAttachment()
        );
        return JdbcTemplate.queryInsertAndReturn(
                ds,
                // language=PostgreSQL
                sql,
                rs -> new Post(
                        rs.getLong("id"),
                        null,
                        rs.getString("content"),
                        rs.getString("attachment"),
                        rs.getLong("created")
                ));
    }

    public void post(PostDto postDto) {
        String query = String.format("INSERT INTO posts (content, attachment) VALUES ('%s', '%s') returning attachment", postDto.getContent(), postDto.getAttachment());
        JdbcTemplate.queryInsert(
                ds,
                // language=PostgreSQL
                query,
                rs -> new Post(
                        null,
                        null,
                        null,
                        null,
                        null));
    }

    public void delete(long postId) {
        String sql = String.format("DELETE FROM posts where id = '%s' returning id", postId);
        JdbcTemplate.queryInsert(
                ds,
                // language=PostgreSQL
                sql,
                rs -> new Post(
                        rs.getLong("id"),
                        null,
                        null,
                        null,
                        null));
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

    public Post update(PostDto postDto) {
        String sql = String.format(
                "UPDATE posts SET content = '%s'," +
                        "attachment = '%s' " +
                        "WHERE id = '%s' RETURNING id, content, attachment",
                postDto.getContent(),
                postDto.getAttachment(),
                postDto.getId()
        );
        return JdbcTemplate.queryInsertAndReturn(
                ds,
                // language=PostgreSQL
                sql,
                rs -> new Post(
                        rs.getLong("id"),
                        null,
                        rs.getString("content"),
                        rs.getString("attachment"),
                        null
                ));
    }
}
