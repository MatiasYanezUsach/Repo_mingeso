package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import proyecto.mingeso.muebles_stgo.entities.RelojEntity;
import proyecto.mingeso.muebles_stgo.repositories.RelojRepository;
import proyecto.mingeso.muebles_stgo.services.RelojService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RelojServiceTest {
    @Mock
    private RelojRepository relojRepository;
    @InjectMocks
    RelojService relojService;

    @Test
    void crearMarca() {
        RelojEntity marca2 = new RelojEntity();
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setRut_empleado("20.580.291-6");
        marca2.setHora(LocalTime.parse("08:00"));
        RelojEntity marca1 = relojService.crearMarca("20.580.291-6", (LocalDate.parse("2022-09-20")), (LocalTime.parse("08:00")));
        assertThat(marca1).isEqualTo(marca2);
    }
    @Test
    void guardarMarca() {
        RelojEntity marca = new RelojEntity();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:00"));
        marca.setRut_empleado("20.580.291-6");
        marca.setId_marca(1L);
        RelojEntity nuevaMarca = new RelojEntity();
        nuevaMarca.setRut_empleado(marca.getRut_empleado());
        nuevaMarca.setFecha(marca.getFecha());
        nuevaMarca.setHora(marca.getHora());
        when (relojRepository.save(any(RelojEntity.class))).thenReturn(marca);
        RelojEntity marcaFinal = relojService.guardarMarca(nuevaMarca);
        assertThat(marcaFinal).isEqualTo(marca);
    }
    @Test
    void guardarJustificativoSinAlgunDato() {
        RelojEntity marca = new RelojEntity();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:00"));
        RelojEntity marcaFinal = relojService.guardarMarca(marca);
        assertThat(marcaFinal).isEqualTo(null);
    }
    @Test
    void obtenerMarcas() {
        RelojEntity marca = new RelojEntity();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setRut_empleado("20.580.291-6");
        marca.setHora(LocalTime.parse("08:00"));
        marca.setId_marca(1L);
        marcas.add(marca);
        when (relojRepository.save(any(RelojEntity.class))).thenReturn(marca);
        RelojEntity marcaFinal = relojRepository.save(marca);
        when (relojRepository.findAll()).thenReturn(marcas);
        assertThat(relojService.obtenerMarcas()).isEqualTo(marcas);
    }
    @Test
    void lecturaArchivoImportado(){
        MockMultipartFile file = new MockMultipartFile("DATA", "Test.txt", "text/plain", "2022/10/03;08:00;20.580.291-6".getBytes());
        int comprobador = relojService.lectura(file);
        assertEquals(1,comprobador,0);
    }
    @Test
    void lecturaArchivoNoImportado(){
        MockMultipartFile file = new MockMultipartFile("DATA", "No.txt", "text/plain", "2022/10/03;08:00;20.580.291-6".getBytes());
        int comprobador = relojService.lectura(file);
        assertEquals(0,comprobador,0);
    }
}