package com.example.herokudemo;

import com.mongodb.MongoClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

  public @Bean MongoClient mongoClient() {
      return new MongoClient("mongodb://<dbuser>:<dbpassword>@ds135413.mlab.com:35413");
  }

  public @Bean MongoTemplate mongoTemplate() {
      return new MongoTemplate(mongoClient(), "heroku_92g8hdl7");
  }
}