package cl.duoc.medicos_service.repository;

import cl.duoc.medicos_service.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //para buscar por la especialidad
    List<Medico> findByEspecialidad(String especialidad);

    //por nombre
    List<Medico> findByNombreContaining(String nombre);


    Optional<Medico> findByEmail(String email);
}