package cl.duoc.historial_clinico_service.mapper;

import cl.duoc.historial_clinico_service.dto.HistorialClinicoDTO;
import cl.duoc.historial_clinico_service.dto.MedicoDTO;
import cl.duoc.historial_clinico_service.dto.PacienteDTO;
import cl.duoc.historial_clinico_service.model.HistorialClinico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistorialClinicoMapper {

    public HistorialClinicoDTO toDTO(
            HistorialClinico entity,
            PacienteDTO paciente,
            MedicoDTO medico
    ) {

        return HistorialClinicoDTO.builder()
                .pacienteNombre(
                        paciente.getNombre()
                )
                .medicoNombre(
                        medico.getNombre()
                )
                .diagnostico(entity.getDiagnostico())
                .fecha(entity.getFecha())
                .build();
    }
}