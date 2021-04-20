package tech.itpark.proggerhub.mapping;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tech.itpark.proggerhub.dto.PostDto;
import tech.itpark.proggerhub.model.Post;
import tech.itpark.proggerhub.model.Post.Author;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post fromDto(PostDto dto) {
        if ( dto == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( dto.getId() );
        post.setAuthor( authorToAuthor( dto.getAuthor() ) );
        post.setContent( dto.getContent() );
        post.setAttachment( dto.getAttachment() );
        post.setCreated( dto.getCreated() );

        return post;
    }

    @Override
    public PostDto fromModel(Post model) {
        if ( model == null ) {
            return null;
        }

        PostDto postDto = new PostDto();

        if ( model.getId() != null ) {
            postDto.setId( model.getId() );
        }
        postDto.setAuthor( authorToAuthor1( model.getAuthor() ) );
        postDto.setContent( model.getContent() );
        postDto.setAttachment( model.getAttachment() );
        if ( model.getCreated() != null ) {
            postDto.setCreated( model.getCreated() );
        }

        return postDto;
    }

    @Override
    public List<Post> fromDtos(List<PostDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( dtos.size() );
        for ( PostDto postDto : dtos ) {
            list.add( fromDto( postDto ) );
        }

        return list;
    }

    @Override
    public List<PostDto> fromModels(List<Post> models) {
        if ( models == null ) {
            return null;
        }

        List<PostDto> list = new ArrayList<PostDto>( models.size() );
        for ( Post post : models ) {
            list.add( fromModel( post ) );
        }

        return list;
    }

    protected Author authorToAuthor(tech.itpark.proggerhub.dto.PostDto.Author author) {
        if ( author == null ) {
            return null;
        }

        Author author1 = new Author();

        author1.setId( author.getId() );
        author1.setAvatar( author.getAvatar() );
        author1.setName( author.getName() );

        return author1;
    }

    protected tech.itpark.proggerhub.dto.PostDto.Author authorToAuthor1(Author author) {
        if ( author == null ) {
            return null;
        }

        tech.itpark.proggerhub.dto.PostDto.Author author1 = new tech.itpark.proggerhub.dto.PostDto.Author();

        author1.setId( author.getId() );
        author1.setAvatar( author.getAvatar() );
        author1.setName( author.getName() );

        return author1;
    }
}
