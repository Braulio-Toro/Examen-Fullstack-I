package cl.duoc.recetas_service.mapper;

import cl.duoc.recetas_service.dto.MedicoDTO;
import cl.duoc.recetas_service.dto.PacienteDTO;
import cl.duoc.recetas_service.dto.RecetaDTO;
import cl.duoc.recetas_service.model.Receta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecetaMapper {

    public RecetaDTO toDTO(
            Receta receta,
            PacienteDTO paciente,
            MedicoDTO medico
    ) {

        return RecetaDTO.builder()
                .id(receta.getId())
                .pacienteNombre(
                        paciente.getNombre()
                )
                .medicoNombre(
                        medico.getNombre()
                )
                .medicamentos(receta.getMedicamentos())
                .fecha(receta.getFecha())
                .build();
    }

    public List<RecetaDTO> toDTOlist(
            List<Receta> recetas,
            List<PacienteDTO> pacientes,
            List<MedicoDTO> medicos
    ) {

        List<RecetaDTO> lista = new ArrayList<>();

        for (int i = 0; i < recetas.size(); i++) {

            lista.add(
                    toDTO(
                            recetas.get(i),
                            pacientes.get(i),
                            medicos.get(i)
                    )
            );
        }

        return lista;
    }
}