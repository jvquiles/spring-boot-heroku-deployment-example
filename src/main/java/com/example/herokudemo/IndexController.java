package com.example.herokudemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String Index() {
        return "Hello there! I'm running.";
    }
    
    @GetMapping("/coment/add")
    public String AddComment() {
        return "Comentario añadido.";
    }
    
    @GetMapping("/coment/delete")
    public String DeleteComment() {
        return "Comentario eliminado.";
    }
    
    @GetMapping("/coment")
    public String Comment() {
        return "Listar comentarios.";
    }
}
