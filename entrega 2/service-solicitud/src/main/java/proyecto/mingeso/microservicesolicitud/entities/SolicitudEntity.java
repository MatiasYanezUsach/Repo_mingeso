package proyecto.mingeso.microservicesolicitud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "solicitudes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_solicitud", nullable = false)
    private Long id_solicitud;
    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate fecha_cubridora;
    private String rut_empleado;
}