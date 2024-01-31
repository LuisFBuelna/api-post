package com.buelna.services;

import com.buelna.client.PostFeignClient;
import com.buelna.exceptions.NotFoundException;
import com.buelna.utils.PostComparator;
import com.buelna.dao.JdbcPostDAO;
import com.buelna.entities.Post;
import com.buelna.entities.PostMongo;
import com.buelna.mapper.PostMapper;
import com.buelna.repositories.PostMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostFeignClient feignClient;

    @Autowired
    private PostMongoRepository mongoRepository;

    @Autowired
    private JdbcPostDAO jdbcPostDAO;

    public List<Post> getAllPost() {

        List<Post> listaJdbc = jdbcPostDAO.getAllPost()
                .orElseThrow(() -> {
                   return new NotFoundException("Lista de elementos no encontrada");
                });

        List<PostMongo> listaMongo = Optional.of(mongoRepository.findAll()).orElseThrow(() -> {
            return new NotFoundException("Lista de elementos no encontrada");
        });

        listaJdbc.addAll(PostMapper.mapper.listPostMongoToPost(listaMongo));

        Collections.sort(listaJdbc, new PostComparator());

        return listaJdbc;
    }

    public HttpStatus insertPosts() {

        List<Post> posts = feignClient.getPostsFeign()
                .orElseThrow(() -> new NotFoundException("Lista de elementos no encontrada"));

        Post[] postsArray = posts.toArray(new Post[posts.size()]);

        Post[] pares = new Post[posts.size() / 2];

        PostMongo[] impares = new PostMongo[posts.size() / 2];

        for (int i = 0; i < postsArray.length; i++) {
            if (i % 2 == 0) {
                pares[i / 2] = postsArray[i];
            } else {
                impares[i / 2] = new PostMongo(postsArray[i].getId(), postsArray[i].getUserId(), postsArray[i].getTitle(), postsArray[i].getBody());
            }
        }

        jdbcPostDAO.savePost(Arrays.stream(pares).toList());

        mongoRepository.saveAll(Arrays.stream(impares).toList());

        return HttpStatus.CREATED;
    }

    public void deleteAll() {
        mongoRepository.deleteAll();
        jdbcPostDAO.deleteAllPost();
    }
}