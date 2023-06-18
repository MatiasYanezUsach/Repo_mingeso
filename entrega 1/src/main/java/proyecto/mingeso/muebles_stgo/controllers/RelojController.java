package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import proyecto.mingeso.muebles_stgo.entities.RelojEntity;
import proyecto.mingeso.muebles_stgo.services.RelojService;

import java.util.ArrayList;

@Controller
public class RelojController {

    @Autowired
    private RelojService relojService;

    @GetMapping("/registroMarcas")
    public String obtenerMarcas(Model model) {
        ArrayList<RelojEntity> marcas = relojService.obtenerMarcas();
        model.addAttribute("marcas",marcas);
        return "RelojMarcas";
    }
}
