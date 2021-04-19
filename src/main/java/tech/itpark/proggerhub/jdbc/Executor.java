package tech.itpark.proggerhub.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Executor<T> {
  T execute(PreparedStatement stmt) throws SQLException;
}
