package com.example.herokudemo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

  public @Bean MongoClient mongoClient() {
    MongoCredential mongoCredential = MongoCredential.createCredential("heroku_92g8hdl7", "heroku_92g8hdl7", "7CX3ZWs2927jc5n".toCharArray());
    ServerAddress serverAddress = new ServerAddress("ds135413.mlab.com", 35413);
		MongoClient client = new MongoClient(serverAddress, mongoCredential, MongoClientOptions.builder().build());

		return client;
  }

  public @Bean MongoTemplate mongoTemplate() {
      return new MongoTemplate(mongoClient(), "canallaDB");
  }
}