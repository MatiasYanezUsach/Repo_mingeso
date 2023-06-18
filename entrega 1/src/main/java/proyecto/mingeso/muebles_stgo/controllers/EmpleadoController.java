package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;

import java.util.ArrayList;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/mostrarEmpleados")
    public String obtenerEmpleados(Model model) {
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        model.addAttribute("empleados",empleados);
        return "Empleados";
    }
}