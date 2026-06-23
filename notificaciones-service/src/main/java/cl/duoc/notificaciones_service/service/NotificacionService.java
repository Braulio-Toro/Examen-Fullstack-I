package cl.duoc.notificaciones_service.service;

import cl.duoc.notificaciones_service.clients.PacienteFeign;
import cl.duoc.notificaciones_service.dto.NotificacionDTO;
import cl.duoc.notificaciones_service.dto.PacienteDTO;
import cl.duoc.notificaciones_service.mapper.NotificacionMapper;
import cl.duoc.notificaciones_service.model.Notificacion;
import cl.duoc.notificaciones_service.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private PacienteFeign pacienteFeign;

    @Autowired
    private NotificacionMapper notificacionMapper;

    public List<NotificacionDTO> getAll() {

        return notificacionRepository.findAll()
                .stream()
                .map(n -> {
                    PacienteDTO paciente =
                            pacienteFeign.buscarPorId(n.getPacienteId());

                    return notificacionMapper.toDTO(n, paciente);
                })
                .toList();
    }

    public Notificacion getById(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    public Notificacion create(Notificacion notificacion) {

        pacienteFeign.buscarPorId(notificacion.getPacienteId());

        return notificacionRepository.save(notificacion);
    }

    public Notificacion update(Long id, Notificacion notificacion) {

        Notificacion existing = getById(id);

        pacienteFeign.buscarPorId(notificacion.getPacienteId());

        existing.setPacienteId(notificacion.getPacienteId());
        existing.setMensaje(notificacion.getMensaje());
        existing.setTipo(notificacion.getTipo());
        existing.setFechaEnvio(notificacion.getFechaEnvio());

        return notificacionRepository.save(existing);
    }

    public void delete(Long id) {
        notificacionRepository.deleteById(id);
    }

    public List<NotificacionDTO> buscarPorPaciente(Long pacienteId) {

        PacienteDTO paciente =
                pacienteFeign.buscarPorId(pacienteId);

        return notificacionRepository.findByPacienteId(pacienteId)
                .stream()
                .map(n -> notificacionMapper.toDTO(n, paciente))
                .toList();
    }

    public List<NotificacionDTO> buscarPorTipo(String tipo) {

        return notificacionRepository.findByTipo(tipo)
                .stream()
                .map(n -> {
                    PacienteDTO paciente =
                            pacienteFeign.buscarPorId(n.getPacienteId());

                    return notificacionMapper.toDTO(n, paciente);
                })
                .toList();
    }

    public List<NotificacionDTO> buscarPorFecha(LocalDate fecha) {

        return notificacionRepository.findByFechaEnvio(fecha)
                .stream()
                .map(n -> {
                    PacienteDTO paciente =
                            pacienteFeign.buscarPorId(n.getPacienteId());

                    return notificacionMapper.toDTO(n, paciente);
                })
                .toList();
    }
}