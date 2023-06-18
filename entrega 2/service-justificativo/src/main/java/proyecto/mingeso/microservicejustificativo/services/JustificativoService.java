package proyecto.mingeso.microservicejustificativo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import proyecto.mingeso.microservicejustificativo.entities.JustificativoEntity;
import proyecto.mingeso.microservicejustificativo.model.Empleado;
import proyecto.mingeso.microservicejustificativo.repositories.JustificativoRepository;

import java.time.LocalDate;

@Service
public class JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;
    @Autowired
    RestTemplate restTemplate;

    public JustificativoEntity guardarJustificativo(JustificativoEntity justificativo){
        JustificativoEntity justificativoNew = justificativoRepository.save(justificativo);
        return justificativoNew;
    }
    public Empleado findByRut(String rut_dado){
        Empleado empleado = new Empleado();
        empleado = restTemplate.getForObject("http://microservice-empleado/empleado/byRut/" + rut_dado, Empleado.class);
        return empleado;
    }

    public JustificativoEntity crearJustificativo(String rut_empleado, LocalDate fecha_cubridora){
        JustificativoEntity nuevoJustificativo = new JustificativoEntity();
        Empleado empleado = findByRut(rut_empleado);
        if(rut_empleado.equals(empleado.getRut())){
            nuevoJustificativo.setRut_empleado(rut_empleado);
            nuevoJustificativo.setFecha_cubridora(fecha_cubridora);
            return nuevoJustificativo;
        }
        else{
            return null;
        }
    }
}