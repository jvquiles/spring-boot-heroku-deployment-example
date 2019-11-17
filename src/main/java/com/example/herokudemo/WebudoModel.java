package com.example.herokudemo;

import javax.persistence.*;

@Entity
public class WebudoModel{
    @Id
    public Integer id;

    public String nombre;
}