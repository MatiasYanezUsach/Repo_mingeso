package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;
import proyecto.mingeso.muebles_stgo.services.JustificativoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class JustificativoController {

    @Autowired
    private JustificativoService justificativoService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private JustificativoRepository justificativoRepository;

    @PostMapping("/subirJustificativo")
    public String subirJustificativo(@RequestParam("rut") String rut_empleado, @RequestParam("fecha") String fecha_cubridora, RedirectAttributes ms) {
        int buscador = 0;
        int cantidadEmpleados = 0;
        int cantidadJustificados = 0;
        int largo_fecha = fecha_cubridora.length();
        if(largo_fecha != 10){
            ms.addFlashAttribute("mensaje2", "La fecha entregada no es valida");
            return "redirect:/";
        }
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        for(EmpleadoEntity cantEmpleados : empleados) {
            cantidadEmpleados++;
        }
        ArrayList<JustificativoEntity> justificativos = justificativoRepository.findByRut(rut_empleado);
        for(JustificativoEntity Justificados : justificativos) {
            cantidadJustificados++;
        }
        for(int i = 0; i< cantidadJustificados;i++){
            if(justificativos.get(i).getFecha_cubridora().equals(LocalDate.parse(fecha_cubridora))){
                ms.addFlashAttribute("mensaje2", "Ya hay un justificativo que cubre esta fecha");
                return "redirect:/";
            }
        }
        for(int i = 0; i < cantidadEmpleados; i++){
            if(Objects.equals(empleados.get(i).getRut(), rut_empleado)){
                JustificativoEntity nuevoJustificativo = justificativoService.crearJustificativo(rut_empleado, LocalDate.parse(fecha_cubridora));
                justificativoService.guardarJustificativo(nuevoJustificativo);
                ms.addFlashAttribute("mensaje2", "Justificativo subido correctamente");
                buscador = 1;
            }
        }
        if (buscador == 1) {
            return "redirect:/";
        }
        else {
            ms.addFlashAttribute("mensaje2", "No fue posible encontrar al empleado solicitado");
            return "redirect:/";
        }
    }
}