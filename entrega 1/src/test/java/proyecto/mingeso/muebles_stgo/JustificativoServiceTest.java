package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;
import proyecto.mingeso.muebles_stgo.services.JustificativoService;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JustificativoServiceTest {
    @Mock
    private JustificativoRepository justificativoRepository;
    @InjectMocks
    JustificativoService justificativoService;

    @Test
    void crearJustificativo() {
        JustificativoEntity justificativo = new JustificativoEntity();
        justificativo.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        justificativo.setRut_empleado("20.580.291-6");
        JustificativoEntity justificativo1 = justificativoService.crearJustificativo("20.580.291-6", (LocalDate.parse("2022-09-20")));
        assertThat(justificativo).isEqualTo(justificativo1);
    }
    @Test
    void guardarJustificativo() {
        JustificativoEntity justificativo = new JustificativoEntity();
        justificativo.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        justificativo.setRut_empleado("20.580.291-6");
        justificativo.setId_justificativo(1L);
        JustificativoEntity justificativoNuevo = new JustificativoEntity();
        justificativoNuevo.setRut_empleado(justificativo.getRut_empleado());
        justificativoNuevo.setFecha_cubridora(justificativo.getFecha_cubridora());
        when (justificativoRepository.save(any(JustificativoEntity.class))).thenReturn(justificativo);
        JustificativoEntity justificativoFinal = justificativoService.guardarJustificativo(justificativoNuevo);
        assertThat(justificativoFinal).isEqualTo(justificativo);
    }
    @Test
    void guardarJustificativoSinAlgunDato() {
        JustificativoEntity justificativo = new JustificativoEntity();
        justificativo.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        JustificativoEntity justificativoFinal = justificativoService.guardarJustificativo(justificativo);
        assertThat(justificativoFinal).isEqualTo(null);
    }
}
