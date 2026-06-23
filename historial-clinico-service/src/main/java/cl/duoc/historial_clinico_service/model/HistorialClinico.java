package cl.duoc.historial_clinico_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "historial_clinico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El paciente no debe estar vacío")
    @Column(name = "paciente_id")
    private Long pacienteId;

    @NotNull(message = "El medico no debe estar vacío")
    @Column(name = "medico_id")
    private Long medicoId;

    @NotBlank(message = "El diagnóstico no debe estar vacío")
    private String diagnostico;

    @NotNull(message = "La fecha no debe estar vacía")
    private LocalDate fecha;
}