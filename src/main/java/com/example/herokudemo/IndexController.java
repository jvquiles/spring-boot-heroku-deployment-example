package com.example.herokudemo;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private DataBase CanallaDB;

    public IndexController(DataBase canallaDB){
        this.CanallaDB = canallaDB;
    }

    @GetMapping("/")
    public String Index() {
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