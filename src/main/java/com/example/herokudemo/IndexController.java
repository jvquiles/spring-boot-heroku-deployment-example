package com.example.herokudemo;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private DataBase CanallaDB;
    private WebudoRepository WebudoRepository;

    public IndexController(DataBase canallaDB, WebudoRepository webudoRepository){
        this.CanallaDB = canallaDB;
        this.WebudoRepository = webudoRepository;
    }

    @GetMapping("/")
    public String Index() {
        return "Bienvenid@! <p><a href=\"/canalla\">Entra en canalla-app.</a></p> <p><p><a href=\"/webudo\">Entra en webudo-app.</a></p>";
    }    
    
    @GetMapping("/canalla")
    public String ListCanalla() {
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
    public String AddCanalla(String canalla) {
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
    public String DeleteCanalla(String canalla) {
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

    @GetMapping("/webudo")
    public String ListWebudo() {
        try
        {
            List<WebudoModel> webudos =  this.WebudoRepository.findAll();
            String listado = "";
            for (WebudoModel WeboModel : webudos) {
                listado += "<li>" + WeboModel.nombre + "</li>";
            }

            return "Los webones son: <ul>" + listado + "</ul>";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @GetMapping("/webudo/añadir")
    public String AddWebudo(String webudo) {
        try
        {
            if (webudo != null) {
                WebudoModel webudoModel = new WebudoModel();
                webudoModel.nombre = webudo;
                this.WebudoRepository.save(webudoModel);
                return "Se ha añadido al webudo de " + webudo;
            }
            else
            {
                return "No se ha añadido al webudo de " + webudo;
            }
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    @GetMapping("/webudo/eliminar")
    public String DeleteWebudo(String canalla) {
        try
        {
            WebudoModel webudoModel= new WebudoModel();
            webudoModel.nombre = canalla;
            List<WebudoModel> webudos = this.WebudoRepository.findAll(Example.of(webudoModel));

            if (webudos.size() > 0) {
                webudoModel = (WebudoModel)webudos.toArray()[0];
                this.WebudoRepository.delete(webudoModel);
                return "Se ha quitado al webudo de " + webudoModel.nombre;
            }
            else {
                return "No se ha quitado al webudo de " + webudoModel.nombre + " porqué no estaba.";
            }
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
}