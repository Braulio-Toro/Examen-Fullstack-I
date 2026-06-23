package cl.duoc.notificaciones_service.mapper;

import cl.duoc.notificaciones_service.dto.NotificacionDTO;
import cl.duoc.notificaciones_service.dto.PacienteDTO;
import cl.duoc.notificaciones_service.model.Notificacion;
import org.springframework.stereotype.Component;

@Component
public class NotificacionMapper {

    public NotificacionDTO toDTO(Notificacion notificacion, PacienteDTO paciente) {

        return NotificacionDTO.builder()
                .id(notificacion.getId())
                .pacienteNombre(paciente.getNombre())
                .mensaje(notificacion.getMensaje())
                .tipo(notificacion.getTipo())
                .fechaEnvio(notificacion.getFechaEnvio())
                .build();
    }

    public Notificacion toEntity(NotificacionDTO dto) {

        Notificacion notificacion = new Notificacion();

        notificacion.setId(dto.getId());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setTipo(dto.getTipo());
        notificacion.setFechaEnvio(dto.getFechaEnvio());

        return notificacion;
    }
}