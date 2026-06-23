package cl.duoc.examenes_service.repository;

import cl.duoc.examenes_service.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findByPacienteId(Long pacienteId);

    List<Examen> findByTipoExamenContaining(String tipoExamen);

    List<Examen> findByFecha(LocalDate fecha);
}