package com.buelna.utils;

import com.buelna.entities.Post;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {

    @Override
    public int compare(Post a, Post b) {
        return a.getId() - b.getId();
    }
}
