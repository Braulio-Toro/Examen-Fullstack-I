package cl.duoc.citas_service.mapper;

import cl.duoc.citas_service.dto.CitaDTO;
import cl.duoc.citas_service.dto.MedicoDTO;
import cl.duoc.citas_service.dto.PacienteDTO;
import cl.duoc.citas_service.model.Cita;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CitaMapper {

    public CitaDTO toDTO(
            Cita cita,
            PacienteDTO paciente,
            MedicoDTO medico
    ){

        return CitaDTO.builder()
                .id(cita.getId())
                .pacienteNombre(
                        paciente.getNombre()
                )
                .medicoNombre(
                        medico.getNombre()
                )
                .fechaHora(cita.getFecha())
                .estado(cita.getEstado())
                .build();
    }

    public List<CitaDTO> toDTOlist(
            List<Cita> citas,
            List<PacienteDTO> pacientes,
            List<MedicoDTO> medicos
    ){

        List<CitaDTO> lista = new ArrayList<>();

        for (int i = 0; i < citas.size(); i++){

            lista.add(
                    toDTO(
                            citas.get(i),
                            pacientes.get(i),
                            medicos.get(i)
                    )
            );
        }

        return lista;
    }
}