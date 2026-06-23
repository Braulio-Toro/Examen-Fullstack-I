package cl.duoc.medicos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del médico no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La especialidad del médico no puede estar vacía")
    private String especialidad;

    @NotBlank(message = "El teléfono del médico no puede estar vacío")
    private String telefono;

    @NotBlank(message = "El email del médico no puede estar vacío")
    private String email;
}