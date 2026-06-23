package cl.duoc.notificaciones_service.repository;

import cl.duoc.notificaciones_service.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByPacienteId(Long pacienteId);

    List<Notificacion> findByTipo(String tipo);

    List<Notificacion> findByFechaEnvio(LocalDate fechaEnvio);
}