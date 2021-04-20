package tech.itpark.proggerhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  private Long id; // boxing/unboxing
  private Author author;
  private String content;
  private String attachment;
  private Long created;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Author {
    private long id;
    private String avatar;
    private String name;
  }
}