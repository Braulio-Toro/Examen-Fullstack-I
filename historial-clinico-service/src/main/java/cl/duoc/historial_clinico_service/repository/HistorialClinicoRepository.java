package cl.duoc.historial_clinico_service.repository;

import cl.duoc.historial_clinico_service.model.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialClinicoRepository
        extends JpaRepository<HistorialClinico, Long> {

    List<HistorialClinico> findByPacienteId(
            Long pacienteId
    );

    List<HistorialClinico> findByMedicoId(
            Long medicoId
    );

    List<HistorialClinico> findByDiagnosticoContaining(
            String diagnostico
    );

    List<HistorialClinico> findByFecha(
            LocalDate fecha
    );
}