package com.buelna.repositories;

import com.buelna.entities.PostMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostMongoRepository extends MongoRepository<PostMongo, Integer> {
}
