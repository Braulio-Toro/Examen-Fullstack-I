package cl.duoc.pacientes_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El rut no puede estar vacío")
    private String rut;
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;
}