package proyecto.mingeso.microservicejustificativo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.microservicejustificativo.entities.JustificativoEntity;

import java.util.ArrayList;

@Repository
public interface JustificativoRepository extends JpaRepository<JustificativoEntity, Long> {
    @Query(value="select * from justificativos as j where j.rut_empleado = :rut_dado",nativeQuery = true)
    ArrayList<JustificativoEntity> findByRut(@Param("rut_dado") String rut_dado);
}