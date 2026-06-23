package cl.duoc.examenes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "examenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El paciente no debe estar vacío")
    private Long pacienteId;

    @NotBlank(message = "El tipo de examen no debe estar vacío")
    private String tipoExamen;

    @NotBlank(message = "El resultado no debe estar vacío")
    private String resultado;

    @NotBlank(message = "El estado no debe estar vacío")
    private String estado;

    @NotNull(message = "La fecha no debe estar vacía")
    private LocalDate fecha;
}