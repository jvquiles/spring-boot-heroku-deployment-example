package com.example.herokudemo;

import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private DataBase CanallaDB;

    public IndexController(){
    }

    @GetMapping("/")
    public String Index() {
        try
        {
            MongoCredential mongoCredential = MongoCredential.createCredential("heroku_92g8hdl7", "canallaDB", "7CX3ZWs2927jc5n".toCharArray());
            ServerAddress serverAddress = new ServerAddress("ds135413.mlab.com", 35413);
            MongoClient client = new MongoClient(serverAddress, mongoCredential, MongoClientOptions.builder().build());
            client.getAddress();
            client.close();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }


        return "Bienvenido a canalla-app.";
    }    
    
    @GetMapping("/canalla")
    public String Comment() {
        try
        {
            List<CanallaModel> canallas =  this.CanallaDB.findAll();
            String listado = "";
            for (CanallaModel canallaModel : canallas) {
                listado += "<li>" + canallaModel.nombre + "</li>";
            }

            return "Los canallas son: <ul>" + listado + "</ul>";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    @GetMapping("/canalla/añadir")
    public String AddComment(String canalla) {
        try
        {
            if (canalla != null) {
                CanallaModel canallaModel = new CanallaModel();
                canallaModel.nombre = canalla;
                this.CanallaDB.insert(canallaModel);
                return "Se ha añadido al canalla de " + canalla;
            }
            else
            {
                return "No se ha añadido al canalla de " + canalla;
            }
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    @GetMapping("/canalla/eliminar")
    public String DeleteComment(String canalla) {
        try
        {
            CanallaModel canallaModel = new CanallaModel();
            canallaModel.nombre = canalla;
            List<CanallaModel> canallas = this.CanallaDB.findAll(Example.of(canallaModel));

            if (canallas.size() > 0) {
                canallaModel = (CanallaModel)canallas.toArray()[0];
                this.CanallaDB.delete(canallaModel);
                return "Se ha quitado al canalla de " + canallaModel.nombre;
            }
            else {
                return "No se ha quitado al canalla de " + canallaModel.nombre + " porqué no estaba.";
            }
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
}