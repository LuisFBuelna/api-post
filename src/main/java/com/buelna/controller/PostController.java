package com.buelna.controller;

import com.buelna.entities.Post;
import com.buelna.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(){
        log.info("Obteniendo lista de posts");
        List<Post> listaElementos = postService.getAllPost();
        return new ResponseEntity<>(listaElementos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insertPosts() {
        log.info("Insertando posts en la base de datos");
        postService.insertPosts();
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPosts() {
        log.info("Eliminando los posts de la base de datos");
        postService.deleteAll();
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}