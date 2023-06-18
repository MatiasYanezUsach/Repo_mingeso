package proyecto.mingeso.muebles_stgo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.muebles_stgo.entities.SueldoEntity;

@Repository
public interface SueldoRepository extends JpaRepository<SueldoEntity, Long> {

}