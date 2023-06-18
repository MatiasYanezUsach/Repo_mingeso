package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proyecto.mingeso.muebles_stgo.entities.RelojEntity;
import proyecto.mingeso.muebles_stgo.repositories.RelojRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class RelojService {
    @Autowired
    RelojRepository relojRepository;

    public RelojEntity crearMarca(String rut_empleado, LocalDate fecha, LocalTime hora){
        RelojEntity nuevaMarca = new RelojEntity();
        nuevaMarca.setRut_empleado(rut_empleado);
        nuevaMarca.setHora(hora);
        nuevaMarca.setFecha(fecha);
        return nuevaMarca;
    }
    public ArrayList<RelojEntity> obtenerMarcas(){
        return (ArrayList<RelojEntity>) relojRepository.findAll();
    }
    public RelojEntity guardarMarca(RelojEntity marca){
        return relojRepository.save(marca);
    }
    public int lectura(MultipartFile file){
        LocalTime hora;
        LocalDate fecha;
        String linea,rut;
        RelojEntity nuevaMarca;
        int id_marca = 1;
        try{
            String folder = "importaciones//";
            Path path = Paths.get( folder +file.getOriginalFilename() );
            BufferedReader archivo = Files.newBufferedReader(path);
            linea = archivo.readLine();
            while(linea != null){
                String[] datos = linea.split(";");
                fecha = LocalDate.parse(datos[0], DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.US));
                hora = LocalTime.parse(datos[1], DateTimeFormatter.ofPattern("HH:mm", Locale.US));
                rut = datos[2];
                nuevaMarca = relojRepository.save(new RelojEntity((long) id_marca, fecha, hora,rut));
                System.out.println(linea);
                linea= archivo.readLine();
                guardarMarca(nuevaMarca);
                id_marca = id_marca + 1;
            }
            return 1;
        } catch (IOException exception){
            System.err.println("el archivo ingresado, no esta previamente importado");
            return 0;
        }
    }
}
