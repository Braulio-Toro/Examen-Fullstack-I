package cl.duoc.medicos_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicoDTO {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La especialidad no puede estar vacía")
    private String especialidad;

    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    private String email;
}
