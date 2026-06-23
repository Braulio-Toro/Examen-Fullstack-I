package cl.duoc.recetas_service.repository;

import cl.duoc.recetas_service.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByPacienteId(Long pacienteId);

    List<Receta> findByMedicamentos(String medicamentos);

    List<Receta> findByFecha(LocalDate fecha);
}