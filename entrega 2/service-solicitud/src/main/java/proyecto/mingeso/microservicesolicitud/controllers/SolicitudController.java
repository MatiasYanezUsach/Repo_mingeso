package proyecto.mingeso.microservicesolicitud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.microservicesolicitud.entities.SolicitudEntity;
import proyecto.mingeso.microservicesolicitud.repositories.SolicitudRepository;
import proyecto.mingeso.microservicesolicitud.services.SolicitudService;

import java.util.ArrayList;

@RestController
@RequestMapping("/solicitud")
public class SolicitudController {
    @Autowired
    SolicitudService solicitudService;
    @Autowired
    SolicitudRepository solicitudRepository;

    @PostMapping("/subirSolicitud")
    public ResponseEntity<SolicitudEntity> subirSolicitud(@RequestBody SolicitudEntity solicitud) {
        int cantidadSolicitudes = 0;
        ArrayList<SolicitudEntity> solicitudes = solicitudRepository.findByRut(solicitud.getRut_empleado());
        for (SolicitudEntity solicitados : solicitudes) {
            cantidadSolicitudes++;
        }
        for (int i = 0; i < cantidadSolicitudes; i++) {
            if (solicitudes.get(i).getFecha_cubridora().equals(solicitud.getFecha_cubridora())) {
                System.out.println ("1");
                return ResponseEntity.badRequest().build();
            }
        }
        SolicitudEntity solicitudFinal = solicitudService.crearSolicitud(solicitud.getRut_empleado(), solicitud.getFecha_cubridora());
        if (solicitudFinal == null) {
            System.out.println ("2");
            return ResponseEntity.badRequest().build();
        } else {
            SolicitudEntity solicitado = solicitudService.guardarSolicitud(solicitudFinal);
            System.out.println ("3");
            return ResponseEntity.ok(solicitado);
        }
    }
    @GetMapping("/byRut/{rut_dado}")
    public ResponseEntity<ArrayList<SolicitudEntity>> obtenerSolicitudes(@PathVariable("rut_dado") String rut_dado) {
        ArrayList<SolicitudEntity> solicitudes = solicitudRepository.findByRut(rut_dado);
        if(solicitudes.isEmpty()){
            return ResponseEntity.ok(solicitudes);
        }
        else{
            return ResponseEntity.ok(solicitudes);
        }
    }
}