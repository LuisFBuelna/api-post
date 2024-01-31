package com.buelna.dao;

import com.buelna.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO<P> {

    Optional<List<Post>> getAllPost();
    void savePost(List<Post> post);

    void deleteAllPost();
}
