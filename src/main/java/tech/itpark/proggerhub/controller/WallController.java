package tech.itpark.proggerhub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.proggerhub.dto.PostDto;
import tech.itpark.proggerhub.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/walls")
@RequiredArgsConstructor
public class WallController {
  private final PostService service;

  // TODO: GET /walls/{id}
  @GetMapping("/getAll")
  // @RequestMapping(method = RequestMethod.GET)
  // ?lastSeenId=xxx&perPage=xxx
  public List<PostDto> getAll(
      @RequestParam(value = "lastSeenId", required = false, defaultValue = "0") long lastSeenId,
      @RequestParam Optional<Integer> perPage
  ) {
    return service.getAll(lastSeenId, perPage.orElse(PostService.MAX_PER_PAGE));
  }

  @PostMapping("/post")
  public PostDto postRowAndReturnParams() {
    return service.postAndReturnParams();
  }

  @GetMapping("/{postId}")
  public PostDto getById(@PathVariable long postId) {
    return service.getById(postId);
  }
}
