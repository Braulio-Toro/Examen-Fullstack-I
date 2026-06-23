package cl.duoc.examenes_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamenDTO {

    private Long id;

    private String pacienteNombre;

    private String tipoExamen;

    private String resultado;

    private String estado;
}