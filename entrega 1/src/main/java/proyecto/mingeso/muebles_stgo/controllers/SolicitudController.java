package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.repositories.SolicitudRepository;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;
import proyecto.mingeso.muebles_stgo.services.SolicitudService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private SolicitudRepository solicitudRepository;

    @PostMapping("/subirSolicitud")
    public String subirSolicitud(@RequestParam("rut") String rut_empleado, @RequestParam("fecha") String fecha_cubridora, RedirectAttributes ms){
        int buscador = 0;
        int cantidadEmpleados = 0;
        int cantidadSolicitudes = 0;
        int largo_fecha = fecha_cubridora.length();
        if(largo_fecha != 10){
            ms.addFlashAttribute("mensaje3", "La fecha entregada no es valida");
            return "redirect:/";
        }
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        for(EmpleadoEntity cantEmpleados : empleados) {
            cantidadEmpleados++;
        }
        ArrayList<SolicitudEntity> solicitudes = solicitudRepository.findByRut(rut_empleado);
        for(SolicitudEntity cantSolicitudes : solicitudes) {
            cantidadSolicitudes++;
        }
        for(int i = 0; i< cantidadSolicitudes;i++){
            if(solicitudes.get(i).getFecha_cubridora().equals(LocalDate.parse(fecha_cubridora))){
                ms.addFlashAttribute("mensaje3", "Ya hay una solicitud que cubre esta fecha");
                return "redirect:/";
            }
        }
        for(int i = 0; i < cantidadEmpleados; i++) {
            if (Objects.equals(empleados.get(i).getRut(), rut_empleado)) {
                SolicitudEntity nuevaSolicitud = solicitudService.crearSolicitud(rut_empleado, LocalDate.parse(fecha_cubridora));
                solicitudService.guardarSolicitud(nuevaSolicitud);
                ms.addFlashAttribute("mensaje3", "Solicitud subida correctamente");
                buscador = 1;
            }
        }
        if (buscador == 1) {
            return "redirect:/";
        }
        else {
            ms.addFlashAttribute("mensaje3", "No fue posible encontrar al empleado solicitado");
            return "redirect:/";
        }
    }
}