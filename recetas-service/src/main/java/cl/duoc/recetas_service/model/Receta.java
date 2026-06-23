package cl.duoc.recetas_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "recetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El Paciente no puede estar vacío")
    private Long pacienteId;

    @NotNull(message = "El medico no puede estar vacío")
    private Long medicoId;

    @NotNull(message = "El medicamento no puede estar vacío")
    private String medicamentos;

    private LocalDate fecha;
}