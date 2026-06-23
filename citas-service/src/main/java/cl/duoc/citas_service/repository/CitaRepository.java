package cl.duoc.citas_service.repository;

import cl.duoc.citas_service.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {

    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findByFecha(LocalDate fecha);
}
