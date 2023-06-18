package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.repositories.EmpleadoRepository;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {
    @Mock
    private EmpleadoRepository empleadoRepository;
    @InjectMocks
    EmpleadoService empleadoService;
    @Test
    void obtenerEmpleados() {
        EmpleadoEntity empleado = new EmpleadoEntity();
        ArrayList<EmpleadoEntity> empleados = new ArrayList<>();
        empleado.setRut("20.580.291-6");
        empleado.setFecha_nac(LocalDate.parse("2001-09-20"));
        empleado.setCategoria("A");
        empleado.setApellidos("Yanez");
        empleado.setNombres("Matias");
        empleado.setFecha_in(LocalDate.parse("2018-09-20"));
        empleado.setId_empleado(1L);
        empleados.add(empleado);
        when (empleadoRepository.save(any(EmpleadoEntity.class))).thenReturn(empleado);
        EmpleadoEntity empleadoFinal = empleadoRepository.save(empleado);
        when (empleadoRepository.findAll()).thenReturn(empleados);
        assertThat(empleadoService.obtenerEmpleados()).isEqualTo(empleados);
    }
}
