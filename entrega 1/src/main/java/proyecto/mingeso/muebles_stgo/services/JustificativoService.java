package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;

import java.time.LocalDate;


@Service
public class JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;

    public JustificativoEntity guardarJustificativo(JustificativoEntity justificativo){
        return justificativoRepository.save(justificativo);
    }
    public JustificativoEntity crearJustificativo(String rut_empleado, LocalDate fecha_cubridora){
        JustificativoEntity nuevoJustificativo = new JustificativoEntity();
        nuevoJustificativo.setRut_empleado(rut_empleado);
        nuevoJustificativo.setFecha_cubridora(fecha_cubridora);
        return nuevoJustificativo;
    }
}
