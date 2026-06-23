package cl.duoc.notificaciones_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El paciente no debe estar vacío")
    private Long pacienteId;

    @NotBlank(message = "El mensaje no debe estar vacío")
    private String mensaje;

    @NotBlank(message = "El tipo no debe estar vacío")
    private String tipo;

    @NotNull(message = "La fecha de envío no debe estar vacía")
    private LocalDate fechaEnvio;
}