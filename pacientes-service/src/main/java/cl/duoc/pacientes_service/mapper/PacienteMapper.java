package cl.duoc.pacientes_service.mapper;

import cl.duoc.pacientes_service.dto.PacienteDTO;
import cl.duoc.pacientes_service.model.Paciente;

public class PacienteMapper {

    public static PacienteDTO toDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .rut(paciente.getRut())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .telefono(paciente.getTelefono())
                .build();
    }

    public static Paciente toEntity(PacienteDTO dto) {
        return Paciente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .rut(dto.getRut())
                .fechaNacimiento(dto.getFechaNacimiento())
                .telefono(dto.getTelefono())
                .build();
    }
}