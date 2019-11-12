package com.example.herokudemo;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

  public @Bean MongoClient mongoClient() {
    List<MongoCredential> allCred = new ArrayList<MongoCredential>();
    MongoCredential mongoCredential = MongoCredential.createCredential("heroku_92g8hdl7", "heroku_92g8hdl7", "7CX3ZWs2927jc5n".toCharArray());
    ServerAddress serverAddress = new ServerAddress("ds135413.mlab.com", 35413);
		MongoClient client = new MongoClient(serverAddress, mongoCredential, MongoClientOptions.builder().build());
		client.setWriteConcern(WriteConcern.ACKNOWLEDGED);

		return client;

      return new MongoClient("mongodb://heroku_92g8hdl7:7CX3ZWs2927jc5n@ds135413.mlab.com:35413/heroku_92g8hdl7");
  }

  public @Bean MongoTemplate mongoTemplate() {
      return new MongoTemplate(mongoClient(), "canallaDB");
  }
}