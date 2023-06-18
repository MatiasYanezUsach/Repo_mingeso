package proyecto.mingeso.microservicesolicitud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import proyecto.mingeso.microservicesolicitud.entities.SolicitudEntity;
import proyecto.mingeso.microservicesolicitud.model.Empleado;
import proyecto.mingeso.microservicesolicitud.repositories.SolicitudRepository;

import java.time.LocalDate;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    RestTemplate restTemplate;

    public SolicitudEntity guardarSolicitud(SolicitudEntity solicitud){
        SolicitudEntity solicitudNew = solicitudRepository.save(solicitud);
        return solicitudNew;
    }

    public Empleado findByRut(String rut_dado){
        Empleado empleado = new Empleado();
        empleado = restTemplate.getForObject("http://microservice-empleado/empleado/byRut/" + rut_dado, Empleado.class);
        return empleado;
    }

    public SolicitudEntity crearSolicitud(String rut_empleado, LocalDate fecha_cubridora){
        SolicitudEntity nuevaSolicitud = new SolicitudEntity();
        Empleado empleado = findByRut(rut_empleado);
        if(rut_empleado.equals(empleado.getRut())){
            nuevaSolicitud.setRut_empleado(rut_empleado);
            nuevaSolicitud.setFecha_cubridora(fecha_cubridora);
            return nuevaSolicitud;
        }
        else{
            return null;
        }
    }
}