package com.buelna.client;

import com.buelna.configuration.FeignClientConfig;
import com.buelna.entities.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "postsClient", url = "https://jsonplaceholder.typicode.com", configuration = FeignClientConfig.class)
public interface PostFeignClient {

    @GetMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Post> getPostsFeign();

    @PostMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HttpStatus> insertPosts (List<Post> posts);
}
