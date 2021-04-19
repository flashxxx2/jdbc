package tech.itpark.proggerhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  private long id; // boxing/unboxing
  private Author author;
  private String content;
  private String attachment;
  // long id = post.getId(); id.toLong() -> NPE
  private long created;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Author {
    private long id;
    private String avatar;
    private String name;
  }
}