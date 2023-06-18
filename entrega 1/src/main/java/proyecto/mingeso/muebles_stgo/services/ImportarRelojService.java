package proyecto.mingeso.muebles_stgo.services;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportarRelojService {
    private final Logger logg = LoggerFactory.getLogger(ImportarRelojService.class);

    public MultipartFile save(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte [] bytes= file.getBytes();
                String folder = "importaciones/";
                Path path = Paths.get( folder +file.getOriginalFilename() );
                System.out.println(path.toAbsolutePath());
                Files.write(path, bytes);
                logg.info("Archivo guardado");
                return file;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}