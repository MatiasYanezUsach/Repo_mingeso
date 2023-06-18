package proyecto.mingeso.microservicesolicitud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.microservicesolicitud.entities.SolicitudEntity;

import java.util.ArrayList;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long> {
    @Query(value="select * from solicitudes as s where s.rut_empleado = :rut_dado",nativeQuery = true)
    ArrayList<SolicitudEntity> findByRut(@Param("rut_dado") String rut_dado);
}