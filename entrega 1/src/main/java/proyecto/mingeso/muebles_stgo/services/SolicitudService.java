package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.repositories.SolicitudRepository;

import java.time.LocalDate;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;

    public SolicitudEntity guardarSolicitud(SolicitudEntity solicitud){
        return solicitudRepository.save(solicitud);
    }
    public SolicitudEntity crearSolicitud(String rut_empleado, LocalDate fecha_cubridora){
        SolicitudEntity nuevaSolicitud = new SolicitudEntity();
        nuevaSolicitud.setRut_empleado(rut_empleado);
        nuevaSolicitud.setFecha_cubridora(fecha_cubridora);
        return nuevaSolicitud;
    }
}