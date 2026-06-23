package cl.duoc.historial_clinico_service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialClinicoDTO {

    private String pacienteNombre;
    private String medicoNombre;
    private String diagnostico;
    private LocalDate fecha;
}