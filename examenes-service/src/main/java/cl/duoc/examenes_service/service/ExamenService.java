package cl.duoc.examenes_service.service;

import cl.duoc.examenes_service.clients.PacienteFeign;
import cl.duoc.examenes_service.dto.ExamenDTO;
import cl.duoc.examenes_service.dto.PacienteDTO;
import cl.duoc.examenes_service.mapper.ExamenMapper;
import cl.duoc.examenes_service.model.Examen;
import cl.duoc.examenes_service.repository.ExamenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private PacienteFeign pacienteFeign;

    @Autowired
    private ExamenMapper examenMapper;

    public List<ExamenDTO> findDTOList() {

        List<Examen> examenes =
                examenRepository.findAll();

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Examen examen : examenes){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            examen.getPacienteId()
                    )
            );
        }

        return examenMapper.toDTOlist(
                examenes,
                pacientes
        );
    }

    public Examen getById(Long id) {

        return examenRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Examen no encontrado"));
    }

    public Examen create(Examen examen) {

        PacienteDTO paciente =
                pacienteFeign.buscarPorId(
                        examen.getPacienteId()
                );

        return examenRepository.save(examen);
    }

    public Examen update(Long id, Examen examen) {

        Examen e = getById(id);

        PacienteDTO paciente =
                pacienteFeign.buscarPorId(
                        examen.getPacienteId()
                );

        e.setPacienteId(examen.getPacienteId());
        e.setTipoExamen(examen.getTipoExamen());
        e.setResultado(examen.getResultado());
        e.setEstado(examen.getEstado());
        e.setFecha(examen.getFecha());

        return examenRepository.save(e);
    }

    public void delete(Long id) {

        examenRepository.deleteById(id);
    }

    public List<ExamenDTO> buscarPorPaciente(Long pacienteId) {

        List<Examen> examenes =
                examenRepository.findByPacienteId(pacienteId);

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Examen examen : examenes){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            examen.getPacienteId()
                    )
            );
        }

        return examenMapper.toDTOlist(
                examenes,
                pacientes
        );
    }

    public List<ExamenDTO> buscarPorTipoExamen(String tipoExamen) {

        List<Examen> examenes =
                examenRepository.findByTipoExamenContaining(tipoExamen);

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Examen examen : examenes){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            examen.getPacienteId()
                    )
            );
        }

        return examenMapper.toDTOlist(
                examenes,
                pacientes
        );
    }

    public List<ExamenDTO> buscarPorFecha(LocalDate fecha) {

        List<Examen> examenes =
                examenRepository.findByFecha(fecha);

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Examen examen : examenes){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            examen.getPacienteId()
                    )
            );
        }

        return examenMapper.toDTOlist(
                examenes,
                pacientes
        );
    }
}