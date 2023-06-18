package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyecto.mingeso.muebles_stgo.entities.*;
import proyecto.mingeso.muebles_stgo.repositories.SueldoRepository;
import proyecto.mingeso.muebles_stgo.services.SueldoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SueldoServiceTest {
    @Mock
    private SueldoRepository sueldoRepository;
    @InjectMocks
    SueldoService sueldoService;
    EmpleadoEntity empleado = new EmpleadoEntity();
    SolicitudEntity solicitud = new SolicitudEntity();
    RelojEntity marca = new RelojEntity();
    RelojEntity marca2 = new RelojEntity();
    JustificativoEntity justificativo = new JustificativoEntity();

    @Test
    void diasHabilesSeptiembre(){
        LocalDate inicioMes = LocalDate.parse("2022-09-01");
        LocalDate finMes = LocalDate.parse("2022-09-30");
        long diasHabiles = sueldoService.diasHabiles(inicioMes, finMes);
        assertEquals(22, diasHabiles,0);
    }
    @Test
    void diasHabilesOctubre(){
        LocalDate inicioMes = LocalDate.parse("2022-10-01");
        LocalDate finMes = LocalDate.parse("2022-10-31");
        long diasHabiles = sueldoService.diasHabiles(inicioMes, finMes);
        assertEquals(21, diasHabiles,0);
    }

    @Test
    void nombreCompletoEmpleado(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        String nombreCompleto = sueldoService.nombreCompletoEmpleado(empleado);
        assertEquals("Yanez Matias", nombreCompleto);
    }

    @Test
    void sueldoFijoMensualCategoriaA(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        double sueldoFijoMensual = sueldoService.calcularSueldoFijoMensual(empleado);
        assertEquals(1700000,sueldoFijoMensual,0);
    }
    @Test
    void sueldoFijoMensualCategoriaB(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("B");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        double sueldoFijoMensual = sueldoService.calcularSueldoFijoMensual(empleado);
        assertEquals(1200000,sueldoFijoMensual,0);
    }
    @Test
    void sueldoFijoMensualCategoriaC(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("C");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        double sueldoFijoMensual = sueldoService.calcularSueldoFijoMensual(empleado);
        assertEquals(800000,sueldoFijoMensual,0);
    }
    @Test
    void sueldoFijoMensualSinCategoria(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        double sueldoFijoMensual = sueldoService.calcularSueldoFijoMensual(empleado);
        assertEquals(0,sueldoFijoMensual,0);
    }
    @Test
    void sueldoFijoMensualCategoriaNoExistente(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("D");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        double sueldoFijoMensual = sueldoService.calcularSueldoFijoMensual(empleado);
        assertEquals(0,sueldoFijoMensual,0);
    }
    @Test
    void montoPagoHorasCategoriaA(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("20:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        double montoHorasExtras = sueldoService.montoPagoHorasExtras(empleado, solicitudes, marcas);
        assertEquals(50000,montoHorasExtras,0);
    }
    @Test
    void montoPagoHorasCategoriaB(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("B");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("20:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        double montoHorasExtras = sueldoService.montoPagoHorasExtras(empleado, solicitudes, marcas);
        assertEquals(40000,montoHorasExtras,0);
    }
    @Test
    void montoPagoHorasCategoriaC(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("C");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("20:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        double montoHorasExtras = sueldoService.montoPagoHorasExtras(empleado, solicitudes, marcas);
        assertEquals(20000,montoHorasExtras,0);
    }
    @Test
    void montoPagoHorasCategoriaNoExiste(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("D");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("20:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        double montoHorasExtras = sueldoService.montoPagoHorasExtras(empleado, solicitudes, marcas);
        assertEquals(0,montoHorasExtras,0);
    }
    @Test
    void montoPagoHorasSinSolicitud(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("20:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        double montoHorasExtras = sueldoService.montoPagoHorasExtras(empleado, solicitudes, marcas);
        assertEquals(0,montoHorasExtras,0);
    }
    @Test
    void calcularAniosServicio(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        int aniosServicio = sueldoService.calcularAniosServicio(empleado);
        assertEquals(4,aniosServicio,0);
    }
    @Test
    void calcularAniosServicioRecienContratado(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2022-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        int aniosServicio = sueldoService.calcularAniosServicio(empleado);
        assertEquals(0,aniosServicio,0);
    }
    @Test
    void calcularAniosServicioAnioSuperiorActual(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2023-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        int aniosServicio = sueldoService.calcularAniosServicio(empleado);
        assertEquals(0,aniosServicio,0);
    }
    @Test
    void montoBonificacionAniosServicioMenor5(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(0,bonificacion,0);
    }
    @Test
    void montoBonificacionAniosServicioMenor10(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2015-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(85000,bonificacion,0);
    }
    @Test
    void montoBonificacionAniosServicioMenor15(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2010-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1980-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(136000,bonificacion,0);
    }
    @Test
    void montoBonificacionAniosServicioMenor20(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2005-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1980-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(187000,bonificacion,0);
    }
    @Test
    void montoBonificacionAniosServicioMenor25(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2000-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1980-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(238000,bonificacion,0);
    }
    @Test
    void montoBonificacionAniosServicioMayor25(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("1990-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1970-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(289000,bonificacion,0);
    }
    @Test
    void montoBonificacionAniosRecienContratado(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2022-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1970-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(0,bonificacion,0);
    }
    @Test
    void montoBonificacionAniosNegativos(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2023-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1970-03-02"));
        double bonificacion = sueldoService.montoBonificacionAniosServicio(empleado);
        assertEquals(0,bonificacion,0);
    }
    @Test
    void montoDescuentoSinAtraso(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(0,montoDescuento,0);
    }
    @Test
    void montoDescuentoAtraso11minutos(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:11"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(17000,montoDescuento,0);
    }
    @Test
    void montoDescuentoAtraso26minutos(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:26"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(51000,montoDescuento,0);
    }
    @Test
    void montoDescuentoAtraso46minutos(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:46"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(102000,montoDescuento,0);
    }
    @Test
    void montoDescuentoAtraso71minutosSinJustificativo(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("09:11"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(255000,montoDescuento,0);
    }
    @Test
    void montoDescuentoAtraso71minutosConJustificativo(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        justificativo.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        justificativo.setRut_empleado("20.580.291-6");
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("09:11"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        justificados.add(justificativo);
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(0,montoDescuento,0);
    }
    @Test
    void montoDescuentoAtrasoInasistenciaSinJustificativo(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(255000,montoDescuento,0);
    }
    @Test
    void montoDescuentoAtrasoInasistenciaConJustificativo(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        justificativo.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        justificativo.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        justificados.add(justificativo);
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        double montoDescuento= sueldoService.montoDescuentosAtrasos(empleado, justificados, marcas,2);
        assertEquals(0,montoDescuento,0);
    }
    @Test
    void calcularSueldoBrutoSinBonificacionesNiDescuentos(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        double sueldoBruto = sueldoService.calcularSueldoBruto(empleado,solicitudes,marcas,justificados,2);
        assertEquals(1700000,sueldoBruto,0);
    }
    @Test
    void calcularSueldoBrutoConBonificacionesyConDescuentos(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2010-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1980-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:46"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("20:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        double sueldoBruto = sueldoService.calcularSueldoBruto(empleado,solicitudes,marcas,justificados,2);
        assertEquals(1784000,sueldoBruto,0);
    }
    @Test
    void calcularCotizacionPrevisionalConSueldoBrutoIntacto(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        double montoPrevisional = sueldoService.calcularCotizacionPrevisional(empleado,solicitudes,marcas,justificados,2);
        assertEquals(170000,montoPrevisional,0);
    }
    @Test
    void calcularCotizacionPrevisionalConSueldoBrutoModificado(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2010-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1980-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:46"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("20:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        double montoPrevisional = sueldoService.calcularCotizacionPrevisional(empleado,solicitudes,marcas,justificados,2);
        assertEquals(178400,montoPrevisional,0);
    }
    @Test
    void calcularCotizacionPlanSaludConSueldoBrutoIntacto(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        double montoSalud = sueldoService.calcularCotizacionPlanSalud(empleado,solicitudes,marcas,justificados,2);
        assertEquals(136000,montoSalud,0);
    }
    @Test
    void calcularCotizacionPlanSaludConSueldoBrutoModificado(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2010-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1980-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:46"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("20:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        double montoSalud = sueldoService.calcularCotizacionPlanSalud(empleado,solicitudes,marcas,justificados,2);
        assertEquals(142720,montoSalud,0);
    }
    @Test
    void calcularSueldoFinalConSueldoBrutoIntacto(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2018-09-09"));
        empleado.setFecha_nac(LocalDate.parse("2001-03-02"));
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:00"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("18:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        double sueldoFinal = sueldoService.calcularSueldoFinal(empleado,solicitudes,marcas,justificados,2);
        assertEquals(1394000,sueldoFinal,0);
    }
    @Test
    void calcularSueldoFinalConSueldoBrutoModificado(){
        empleado.setRut("20.580.291-6");
        empleado.setNombres("Matias");
        empleado.setApellidos("Yanez");
        empleado.setCategoria("A");
        empleado.setFecha_in(LocalDate.parse("2010-09-09"));
        empleado.setFecha_nac(LocalDate.parse("1980-03-02"));
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        ArrayList<JustificativoEntity> justificados = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setHora(LocalTime.parse("08:46"));
        marca.setRut_empleado("20.580.291-6");
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marcas.add(marca);
        marca2.setFecha(LocalDate.parse("2022-09-20"));
        marca2.setHora(LocalTime.parse("20:00"));
        marca2.setRut_empleado("20.580.291-6");
        marcas.add(marca2);
        ArrayList<SolicitudEntity> solicitudes = new ArrayList<>();
        solicitudes.add(solicitud);
        double sueldoFinal = sueldoService.calcularSueldoFinal(empleado,solicitudes,marcas,justificados,2);
        assertEquals(1462880,sueldoFinal,0);
    }
    @Test
    void obtenerPlanilla() {
        SueldoEntity sueldo = new SueldoEntity();
        ArrayList<SueldoEntity> sueldos = new ArrayList<>();
        sueldo.setRut("20.580.291-6");
        sueldo.setCategoria("A");
        sueldo.setSueldo_bruto(1700000);
        sueldo.setMonto_sueldo_final(1530000);
        sueldo.setCotizacion_previsional(170000);
        sueldo.setCotizacion_salud(136000);
        sueldo.setAnios_servicio(0);
        sueldo.setMonto_bonificacion_anios_servicio(0);
        sueldo.setSueldo_fijo_mensual(1700000);
        sueldo.setMonto_descuentos(0);
        sueldo.setMonto_pago_horas_extras(0);
        sueldo.setNombre_empleado("Yanez Matias");
        sueldos.add(sueldo);
        when (sueldoRepository.save(any(SueldoEntity.class))).thenReturn(sueldo);
        SueldoEntity sueldoFinal = sueldoRepository.save(sueldo);
        when (sueldoRepository.findAll()).thenReturn(sueldos);
        assertThat(sueldoService.obtenerPlanilla()).isEqualTo(sueldos);
    }
}