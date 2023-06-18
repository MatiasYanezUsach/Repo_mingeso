package proyecto.mingeso.microserviceempleado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.microserviceempleado.entities.EmpleadoEntity;

import java.util.ArrayList;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    @Query(value="select * from empleados as e where e.rut = :rut_dado",nativeQuery = true)
    EmpleadoEntity findByRut(@Param("rut_dado") String rut_dado);
}