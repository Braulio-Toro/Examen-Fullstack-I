package cl.duoc.medicos_service.mapper;

import cl.duoc.medicos_service.dto.MedicoDTO;
import cl.duoc.medicos_service.model.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public MedicoDTO toDTO(Medico medico) {
        if (medico == null) {
            return null;
        }

        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNombre(medico.getNombre());
        dto.setEspecialidad(medico.getEspecialidad());
        dto.setTelefono(medico.getTelefono());
        dto.setEmail(medico.getEmail());

        return dto;
    }

    public Medico toEntity(MedicoDTO dto) {
        if (dto == null) {
            return null;
        }

        Medico medico = new Medico();
        medico.setId(dto.getId());
        medico.setNombre(dto.getNombre());
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setTelefono(dto.getTelefono());
        medico.setEmail(dto.getEmail());

        return medico;
    }
}