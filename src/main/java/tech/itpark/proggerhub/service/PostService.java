package tech.itpark.proggerhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.proggerhub.dto.PostDto;
import tech.itpark.proggerhub.exception.NotFoundException;
import tech.itpark.proggerhub.mapping.PostMapper;
import tech.itpark.proggerhub.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
  public static final int MAX_PER_PAGE = 10;
  private final PostRepository repository;
  private final PostMapper postMapper;


  public List<PostDto> getAll(long lastSeenId, int perPage) {
    return postMapper.fromModels(repository.getAll());
  }

  public PostDto postAndReturnParams(String content, String attachment) {
    return postMapper.fromModel(repository.postAndReturn(content, attachment));
  }

  public PostDto post(String content, String attachment) {
    return postMapper.fromModel(repository.post(content, attachment));
  }

  public PostDto getById(long postId) {
    return Optional.ofNullable(postMapper.fromModel(repository.get(postId))).orElseThrow(NotFoundException::new);
  }

  public void deleteById(long postId) {
    repository.delete(postId);
  }
}
