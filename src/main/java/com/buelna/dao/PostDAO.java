package com.buelna.dao;

import com.buelna.entities.Post;

import java.util.List;

public interface PostDAO<P> {

    List<Post> getAllPost();
    void savePost(List<Post> post);

    void deleteAllPost();
}
