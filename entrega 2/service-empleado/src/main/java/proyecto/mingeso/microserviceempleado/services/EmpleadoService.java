package proyecto.mingeso.microserviceempleado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.microserviceempleado.entities.EmpleadoEntity;
import proyecto.mingeso.microserviceempleado.repositories.EmpleadoRepository;

import java.util.ArrayList;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
    }
    public EmpleadoEntity findByRut(String rut_dado){
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado = empleadoRepository.findByRut(rut_dado);
        return empleado;
    }
}