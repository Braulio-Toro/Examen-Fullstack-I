package cl.duoc.citas_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaDTO {

    private Long id;

    private String pacienteNombre;

    private String medicoNombre;

    private LocalDateTime fechaHora;

    private String estado;
}