package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import proyecto.mingeso.muebles_stgo.services.ImportarRelojService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ImportarRelojServiceTest {
    @InjectMocks
    ImportarRelojService importarRelojService;
    @Test
    void importarArchivoVacio(){
        String contenido = "";
        MockMultipartFile file = new MockMultipartFile("DATOS", contenido.getBytes());
        MultipartFile filefinal= importarRelojService.save(file);
        assertNull(filefinal);
    }
    @Test
    void importarArchivoConContenidodeNombreDATOStxt() {
        String contenido = "2022/10/03;08:00;20.580.291-6";
        MockMultipartFile file = new MockMultipartFile("DATOS", "Test.txt", "text/plain", "2022/10/03;08:00;20.580.291-6".getBytes());
        MultipartFile filefinal= importarRelojService.save(file);
        assertEquals(file, filefinal);
    }
    @Test
    void importarArchivoConContenidodeNombreDATAtxt() {
        String contenido = "2022/10/03;08:00;20.580.291-6";
        MockMultipartFile file = new MockMultipartFile("DATA", "Test.txt", "text/plain", "2022/10/03;08:00;20.580.291-6".getBytes());
        MultipartFile filefinal= importarRelojService.save(file);
        assertEquals(file, filefinal);
    }
}
