package com.buelna.mapper;

import com.buelna.entities.Post;
import com.buelna.entities.PostMongo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper mapper = Mappers.getMapper(PostMapper.class);

    List<Post> listPostMongoToPost(List<PostMongo> post);
}
