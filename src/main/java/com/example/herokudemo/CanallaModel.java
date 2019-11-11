package com.example.herokudemo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CanallaModel{
    @Id
    public ObjectId _id;
    
	public String nombre;
}