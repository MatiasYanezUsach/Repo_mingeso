package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.entities.SueldoEntity;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;
import proyecto.mingeso.muebles_stgo.services.SueldoService;

import java.util.ArrayList;

@Controller
public class SueldoController {
    @Autowired
    private SueldoService sueldoService;
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/crearPlanilla")
    public String obtenerPlanillas(Model model) {
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        sueldoService.sueldosGenerales(empleados);
        ArrayList<SueldoEntity> sueldos = sueldoService.obtenerPlanilla();
        model.addAttribute("sueldos",sueldos);
        return "Planilla";
    }
}
