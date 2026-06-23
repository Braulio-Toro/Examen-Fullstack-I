package cl.duoc.citas_service.service;

import cl.duoc.citas_service.clients.MedicoFeign;
import cl.duoc.citas_service.clients.PacienteFeign;
import cl.duoc.citas_service.dto.CitaDTO;
import cl.duoc.citas_service.dto.MedicoDTO;
import cl.duoc.citas_service.dto.PacienteDTO;
import cl.duoc.citas_service.mapper.CitaMapper;
import cl.duoc.citas_service.model.Cita;
import cl.duoc.citas_service.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private PacienteFeign pacienteFeign;

    @Autowired
    private MedicoFeign medicoFeign;

    public List<CitaDTO> findDTOList() {

        List<Cita> citas = citaRepository.findAll();

        List<PacienteDTO> pacientes = new ArrayList<>();
        List<MedicoDTO> medicos = new ArrayList<>();

        for (Cita cita : citas){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            cita.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            cita.getMedicoId()
                    )
            );
        }

        return citaMapper.toDTOlist(
                citas,
                pacientes,
                medicos
        );
    }

    public Cita getById(Long id) {

        return citaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cita no encontrada"
                        )
                );
    }

    public Cita create(Cita cita) {

        PacienteDTO paciente =
                pacienteFeign.buscarPorId(
                        cita.getPacienteId()
                );

        if (paciente == null) {
            throw new RuntimeException(
                    "El paciente no existe"
            );
        }

        MedicoDTO medico =
                medicoFeign.buscarPorId(
                        cita.getMedicoId()
                );

        if (medico == null) {
            throw new RuntimeException(
                    "El medico no existe"
            );
        }

        return citaRepository.save(cita);
    }

    public Cita update(Long id, Cita cita) {

        Cita citaActualizar = getById(id);

        PacienteDTO paciente =
                pacienteFeign.buscarPorId(
                        cita.getPacienteId()
                );

        if (paciente == null) {
            throw new RuntimeException(
                    "El paciente no existe"
            );
        }

        MedicoDTO medico =
                medicoFeign.buscarPorId(
                        cita.getMedicoId()
                );

        if (medico == null) {
            throw new RuntimeException(
                    "El medico no existe"
            );
        }

        citaActualizar.setPacienteId(
                cita.getPacienteId()
        );

        citaActualizar.setMedicoId(
                cita.getMedicoId()
        );

        citaActualizar.setFecha(
                cita.getFecha()
        );

        citaActualizar.setEstado(
                cita.getEstado()
        );

        return citaRepository.save(
                citaActualizar
        );
    }

    public void delete(Long id) {

        citaRepository.deleteById(id);
    }

    public List<CitaDTO> buscarPorMedico(Long medicoId) {

        List<Cita> citas =
                citaRepository.findByMedicoId(medicoId);

        List<PacienteDTO> pacientes = new ArrayList<>();
        List<MedicoDTO> medicos = new ArrayList<>();

        for (Cita cita : citas){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            cita.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            cita.getMedicoId()
                    )
            );
        }

        return citaMapper.toDTOlist(
                citas,
                pacientes,
                medicos
        );
    }

    public List<CitaDTO> buscarPorPaciente(Long pacienteId) {

        List<Cita> citas =
                citaRepository.findByPacienteId(pacienteId);

        List<PacienteDTO> pacientes = new ArrayList<>();
        List<MedicoDTO> medicos = new ArrayList<>();

        for (Cita cita : citas){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            cita.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            cita.getMedicoId()
                    )
            );
        }

        return citaMapper.toDTOlist(
                citas,
                pacientes,
                medicos
        );
    }

    public List<CitaDTO> buscarPorFecha(LocalDate fecha) {

        List<Cita> citas =
                citaRepository.findByFecha(fecha);

        List<PacienteDTO> pacientes = new ArrayList<>();
        List<MedicoDTO> medicos = new ArrayList<>();

        for (Cita cita : citas){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            cita.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            cita.getMedicoId()
                    )
            );
        }

        return citaMapper.toDTOlist(
                citas,
                pacientes,
                medicos
        );
    }
}