package cl.duoc.historial_clinico_service.service;

import cl.duoc.historial_clinico_service.clients.MedicoFeign;
import cl.duoc.historial_clinico_service.clients.PacienteFeign;
import cl.duoc.historial_clinico_service.dto.HistorialClinicoDTO;
import cl.duoc.historial_clinico_service.dto.MedicoDTO;
import cl.duoc.historial_clinico_service.dto.PacienteDTO;
import cl.duoc.historial_clinico_service.mapper.HistorialClinicoMapper;
import cl.duoc.historial_clinico_service.model.HistorialClinico;
import cl.duoc.historial_clinico_service.repository.HistorialClinicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialClinicoService {

    @Autowired
    private HistorialClinicoRepository repository;

    @Autowired
    private HistorialClinicoMapper mapper;

    @Autowired
    private PacienteFeign pacienteFeign;

    @Autowired
    private MedicoFeign medicoFeign;

    public List<HistorialClinicoDTO> obtenerTodos() {

        List<HistorialClinico> historiales =
                repository.findAll();

        List<HistorialClinicoDTO> lista =
                new ArrayList<>();

        for (HistorialClinico historial : historiales) {

            PacienteDTO paciente =
                    pacienteFeign.buscarPorId(
                            historial.getPacienteId()
                    );

            MedicoDTO medico =
                    medicoFeign.buscarPorId(
                            historial.getMedicoId()
                    );

            lista.add(
                    mapper.toDTO(
                            historial,
                            paciente,
                            medico
                    )
            );
        }

        return lista;
    }

    public HistorialClinico obtenerPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Historial clínico no encontrado")
                );
    }

    public HistorialClinico guardarHistorial(HistorialClinico historial) {

        pacienteFeign.buscarPorId(
                historial.getPacienteId()
        );

        medicoFeign.buscarPorId(
                historial.getMedicoId()
        );

        return repository.save(historial);
    }

    public HistorialClinico actualizarHistorial(Long id, HistorialClinico historial) {

        HistorialClinico existente = obtenerPorId(id);

        pacienteFeign.buscarPorId(
                historial.getPacienteId()
        );

        medicoFeign.buscarPorId(
                historial.getMedicoId()
        );

        existente.setPacienteId(historial.getPacienteId());
        existente.setMedicoId(historial.getMedicoId());
        existente.setDiagnostico(historial.getDiagnostico());
        existente.setFecha(historial.getFecha());

        return repository.save(existente);
    }

    public void eliminarHistorial(Long id) {

        repository.deleteById(id);
    }

    public List<HistorialClinicoDTO> buscarPorPaciente(Long pacienteId) {

        List<HistorialClinico> historiales =
                repository.findByPacienteId(pacienteId);

        List<HistorialClinicoDTO> lista =
                new ArrayList<>();

        for (HistorialClinico historial : historiales) {

            PacienteDTO paciente =
                    pacienteFeign.buscarPorId(
                            historial.getPacienteId()
                    );

            MedicoDTO medico =
                    medicoFeign.buscarPorId(
                            historial.getMedicoId()
                    );

            lista.add(
                    mapper.toDTO(
                            historial,
                            paciente,
                            medico
                    )
            );
        }

        return lista;
    }

    public List<HistorialClinicoDTO> buscarPorMedico(Long medicoId) {

        List<HistorialClinico> historiales =
                repository.findByMedicoId(medicoId);

        List<HistorialClinicoDTO> lista =
                new ArrayList<>();

        for (HistorialClinico historial : historiales) {

            PacienteDTO paciente =
                    pacienteFeign.buscarPorId(
                            historial.getPacienteId()
                    );

            MedicoDTO medico =
                    medicoFeign.buscarPorId(
                            historial.getMedicoId()
                    );

            lista.add(
                    mapper.toDTO(
                            historial,
                            paciente,
                            medico
                    )
            );
        }

        return lista;
    }

    public List<HistorialClinicoDTO> buscarPorDiagnostico(String diagnostico) {

        List<HistorialClinico> historiales =
                repository.findByDiagnosticoContaining(diagnostico);

        List<HistorialClinicoDTO> lista =
                new ArrayList<>();

        for (HistorialClinico historial : historiales) {

            PacienteDTO paciente =
                    pacienteFeign.buscarPorId(
                            historial.getPacienteId()
                    );

            MedicoDTO medico =
                    medicoFeign.buscarPorId(
                            historial.getMedicoId()
                    );

            lista.add(
                    mapper.toDTO(
                            historial,
                            paciente,
                            medico
                    )
            );
        }

        return lista;
    }

    public List<HistorialClinicoDTO> buscarPorFecha(LocalDate fecha) {

        List<HistorialClinico> historiales =
                repository.findByFecha(fecha);

        List<HistorialClinicoDTO> lista =
                new ArrayList<>();

        for (HistorialClinico historial : historiales) {

            PacienteDTO paciente =
                    pacienteFeign.buscarPorId(
                            historial.getPacienteId()
                    );

            MedicoDTO medico =
                    medicoFeign.buscarPorId(
                            historial.getMedicoId()
                    );

            lista.add(
                    mapper.toDTO(
                            historial,
                            paciente,
                            medico
                    )
            );
        }

        return lista;
    }
}