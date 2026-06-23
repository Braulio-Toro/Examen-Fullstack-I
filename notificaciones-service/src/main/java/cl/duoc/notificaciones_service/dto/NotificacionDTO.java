package cl.duoc.notificaciones_service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionDTO {

    private Long id;
    private String pacienteNombre;
    private String mensaje;
    private String tipo;
    private LocalDate fechaEnvio;
}