package tech.itpark.proggerhub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.proggerhub.dto.PostDto;
import tech.itpark.proggerhub.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/walls")
@RequiredArgsConstructor
public class WallController {

  private final PostService service;

  @GetMapping("/getAll")
  public List<PostDto> getAll(
      @RequestParam(value = "lastSeenId", required = false, defaultValue = "0") long lastSeenId,
      @RequestParam Optional<Integer> perPage
  ) {
    return service.getAll(lastSeenId, perPage.orElse(PostService.MAX_PER_PAGE));
  }

  @PostMapping("/return")
  public PostDto postRowAndReturnParams(@RequestParam String content,
                                        @RequestParam String attachment
  ) {
    return service.postAndReturnParams(content, attachment);
  }

  @PostMapping("/post")
  public PostDto post(@RequestParam String content,
                      @RequestParam String attachment
  ) {
    return service.post(content, attachment);
  }

  @GetMapping("/{postId}")
  public PostDto getById(@PathVariable long postId) {
    return service.getById(postId);
  }

  @DeleteMapping("/{postId}")
  public void deleteById(@PathVariable long postId) {
    service.deleteById(postId);
  }
}
