package tech.itpark.proggerhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.proggerhub.dto.PostDto;
import tech.itpark.proggerhub.exception.NotFoundException;
import tech.itpark.proggerhub.mapping.PostMapper;
import tech.itpark.proggerhub.model.Post;
import tech.itpark.proggerhub.repository.PostRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
  public static final int MAX_PER_PAGE = 10;
  private final PostRepository repository;
  private final PostMapper postMapper;


  public List<PostDto> getAll(long lastSeenId, int perPage) {
    return postMapper.fromModels(repository.getAll());
  }

  public PostDto postAndReturnParams() {
    return postMapper.fromModel(repository.post());
  }

  public PostDto getById(long postId) {
    return null;
  }
}
