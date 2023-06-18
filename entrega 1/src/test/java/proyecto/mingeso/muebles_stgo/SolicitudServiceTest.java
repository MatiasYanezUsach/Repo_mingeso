package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.repositories.SolicitudRepository;
import proyecto.mingeso.muebles_stgo.services.SolicitudService;

import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SolicitudServiceTest {
    @Mock
    private SolicitudRepository solicitudRepository;
    @InjectMocks
    SolicitudService solicitudService;

    @Test
    void crearSolicitud() {
        SolicitudEntity solicitud = new SolicitudEntity();
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        SolicitudEntity nuevaSolicitud = solicitudService.crearSolicitud("20.580.291-6", (LocalDate.parse("2022-09-20")));
        assertThat(solicitud).isEqualTo(nuevaSolicitud);
    }
    @Test
    void guardarSolicitud() {
        SolicitudEntity solicitud = new SolicitudEntity();
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        solicitud.setId_solicitud(1L);
        SolicitudEntity nuevaSolicitud = new SolicitudEntity();
        nuevaSolicitud.setRut_empleado(solicitud.getRut_empleado());
        nuevaSolicitud.setFecha_cubridora(solicitud.getFecha_cubridora());
        when (solicitudRepository.save(any(SolicitudEntity.class))).thenReturn(solicitud);
        SolicitudEntity solicitudFinal = solicitudService.guardarSolicitud(nuevaSolicitud);
        assertThat(solicitudFinal).isEqualTo(solicitud);
    }
    @Test
    void guardarSolicitudSinAlgunDato() {
        SolicitudEntity solicitud = new SolicitudEntity();
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        SolicitudEntity solicitudFinal = solicitudService.guardarSolicitud(solicitud);
        assertThat(solicitudFinal).isEqualTo(null);
    }
}