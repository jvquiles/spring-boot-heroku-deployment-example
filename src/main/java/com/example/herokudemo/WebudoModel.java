package com.example.herokudemo;

import javax.persistence.*;

@Entity
public class WebudoModel{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;

    public String nombre;
}