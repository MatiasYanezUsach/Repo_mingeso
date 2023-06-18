package proyecto.mingeso.muebles_stgo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empleado", nullable = false)
    private Long id_empleado;
    private String rut;
    private String apellidos;
    private String nombres;
    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate fecha_nac;
    private String categoria;
    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate fecha_in;
}