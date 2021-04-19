package tech.itpark.proggerhub.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tech.itpark.proggerhub.dto.PostDto;
import tech.itpark.proggerhub.model.Post;

import java.util.List;

@Mapper
public interface PostMapper {
  Post fromDto(PostDto dto);
  PostDto fromModel(Post model);

  List<Post> fromDtos(List<PostDto> dtos);
  List<PostDto> fromModels(List<Post> models);

  List<PostDto> fromDto(Post posts);
}
