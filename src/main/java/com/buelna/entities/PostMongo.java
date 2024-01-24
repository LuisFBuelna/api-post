package com.buelna.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PostMongo {

    @Id
    private Integer id;

    private Integer userId;

    private String title;

    private String body;

    public PostMongo(Integer id, Integer userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
