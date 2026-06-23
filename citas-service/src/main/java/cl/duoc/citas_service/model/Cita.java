package cl.duoc.citas_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El paciente no debe estar vacío")
    private Long pacienteId;

    @NotNull(message = "El medico no debe estar vacío")
    private Long medicoId;

    @NotNull(message = "La fecha y hora no debe estar vacía")
    private LocalDateTime fecha;

    @NotBlank(message = "El estado no debe estar vacío")
    private String estado;
}