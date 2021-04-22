package tech.itpark.proggerhub.jdbc;

import tech.itpark.proggerhub.model.Post;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

// pstms -> set(i, x) -> 1 to count(?)
// CRUD
// TODO:
//  1. Часть
//  query умеет RETURNING (INSERT ... RETURNING id, created)
//  SELECT 1 запись -> Optional
//  UPDATE с параметрами
//  DELETE
//  INSERT
//  2. CRUD на посты Wall -> attachment | resource server own /<data>/uuid.png
public interface JdbcTemplate {
    static <T> List<T> query(DataSource ds, String sql, RowMapper<T> rowMapper, Object... args) {
        return execute(ds, sql, args, stmt -> {
            try (
                    final var rs = stmt.executeQuery();
            ) {
                List<T> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(rowMapper.mapRow(rs));
                }
                return result;
            }
        });
    }

    static Post queryInsertAndReturn(DataSource ds, String sql, RowMapper<Post> rowMapper, Object... args) {
        return execute(ds, sql, args, stmt -> {
            try (
                    final var rs = stmt.executeQuery();
            ) {
                if (rs.next()) {
                    return rowMapper.mapRow(rs);
                }
            }
            return null;
        });
    }

    static void queryInsert(DataSource ds, String sql, RowMapper<Post> rowMapper) {
        execute(ds, sql, stmt -> {
            try (
                    final var rs = stmt.executeQuery();
            ) {
                if (rs.next()) {
                    return rowMapper.mapRow(rs);
                }
            }
            return null;
        });
    }

    private static <T> T execute(DataSource ds, String sql, Object[] args, Executor<T> executor) {
        try (
                final var conn = ds.getConnection();
                final var stmt = conn.prepareStatement(sql);
        ) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                stmt.setObject(i + 1, arg);
            }

            return executor.execute(stmt);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }


    private static <T> T execute(DataSource ds, String sql, Executor<T> executor) {
        try (
                final var conn = ds.getConnection();
                final var stmt = conn.prepareStatement(sql);
        ) {

            return executor.execute(stmt);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
