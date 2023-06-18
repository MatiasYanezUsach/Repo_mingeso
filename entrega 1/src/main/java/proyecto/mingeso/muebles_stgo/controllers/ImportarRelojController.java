package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.mingeso.muebles_stgo.services.ImportarRelojService;
import proyecto.mingeso.muebles_stgo.services.RelojService;

import java.util.Objects;


@Controller
public class ImportarRelojController {

    @Autowired
    private ImportarRelojService importarRelojService;
    @Autowired
    private RelojService relojService;

    @GetMapping("/")
    public String Reloj() {
        return "Home";
    }

    @PostMapping("/importar")
    public String importarArchivos( @RequestParam("archivos") MultipartFile file, RedirectAttributes ms) {
        String nombreArchivo= file.getOriginalFilename();
        if (Objects.equals(nombreArchivo, "DATOS.txt") ||Objects.equals(nombreArchivo, "DATA.txt") ) {
            relojService.lectura(importarRelojService.save(file));
            ms.addFlashAttribute("mensaje", "Archivo importado correctamente");
        }
        else{
            ms.addFlashAttribute("mensaje", "El archivo ingresado no puede ser importado");
        }
        return "redirect:/";
    }
}