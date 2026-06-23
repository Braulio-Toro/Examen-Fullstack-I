package cl.duoc.examenes_service.mapper;

import cl.duoc.examenes_service.dto.ExamenDTO;
import cl.duoc.examenes_service.dto.PacienteDTO;
import cl.duoc.examenes_service.model.Examen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamenMapper {

    public ExamenDTO toDTO(
            Examen examen,
            PacienteDTO paciente
    ) {

        return ExamenDTO.builder()
                .id(examen.getId())
                .pacienteNombre(paciente.getNombre())
                .tipoExamen(examen.getTipoExamen())
                .resultado(examen.getResultado())
                .estado(examen.getEstado())
                .build();
    }

    public List<ExamenDTO> toDTOlist(
            List<Examen> examenes,
            List<PacienteDTO> pacientes
    ) {

        List<ExamenDTO> lista = new ArrayList<>();

        for (int i = 0; i < examenes.size(); i++) {

            lista.add(
                    toDTO(
                            examenes.get(i),
                            pacientes.get(i)
                    )
            );
        }

        return lista;
    }
}