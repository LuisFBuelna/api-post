package com.buelna.controller;

import com.buelna.entities.Post;
import com.buelna.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(){
        List<Post> listaElementos = postService.getAllPost();
        return new ResponseEntity<>(listaElementos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insertPosts() {
        postService.insertPosts();
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPosts() {
        postService.deleteAll();
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
