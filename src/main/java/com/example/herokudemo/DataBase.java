package com.example.herokudemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBase extends MongoRepository<CanallaModel, String> {
}