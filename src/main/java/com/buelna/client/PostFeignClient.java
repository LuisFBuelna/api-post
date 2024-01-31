package com.buelna.client;

import com.buelna.entities.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "postsClient", url = "https://jsonplaceholder.typicode.com")
public interface PostFeignClient {

    @GetMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<List<Post>> getPostsFeign();

    @PostMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HttpStatus> insertPosts (List<Post> posts);
}
