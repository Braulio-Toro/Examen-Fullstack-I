package cl.duoc.recetas_service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaDTO {

    private Long id;
    private String pacienteNombre;
    private String medicoNombre;
    private String medicamentos;
    private LocalDate fecha;
}