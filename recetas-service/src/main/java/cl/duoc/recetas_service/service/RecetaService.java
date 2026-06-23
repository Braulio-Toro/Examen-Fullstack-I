package cl.duoc.recetas_service.service;

import cl.duoc.recetas_service.clients.MedicoFeign;
import cl.duoc.recetas_service.clients.PacienteFeign;
import cl.duoc.recetas_service.dto.MedicoDTO;
import cl.duoc.recetas_service.dto.PacienteDTO;
import cl.duoc.recetas_service.dto.RecetaDTO;
import cl.duoc.recetas_service.mapper.RecetaMapper;
import cl.duoc.recetas_service.model.Receta;
import cl.duoc.recetas_service.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private RecetaMapper recetaMapper;

    @Autowired
    private PacienteFeign pacienteFeign;

    @Autowired
    private MedicoFeign medicoFeign;

    public List<RecetaDTO> findDTOList() {

        List<Receta> recetas = recetaRepository.findAll();

        List<PacienteDTO> pacientes = new ArrayList<>();
        List<MedicoDTO> medicos = new ArrayList<>();

        for (Receta receta : recetas) {

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            receta.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            receta.getMedicoId()
                    )
            );
        }

        return recetaMapper.toDTOlist(
                recetas,
                pacientes,
                medicos
        );
    }

    public Receta getById(Long id) {

        return recetaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Receta no encontrada"
                        )
                );
    }

    public Receta create(Receta receta) {

        pacienteFeign.buscarPorId(
                receta.getPacienteId()
        );

        medicoFeign.buscarPorId(
                receta.getMedicoId()
        );

        return recetaRepository.save(receta);
    }

    public Receta update(Long id, Receta receta) {

        Receta existing = getById(id);

        pacienteFeign.buscarPorId(
                receta.getPacienteId()
        );

        medicoFeign.buscarPorId(
                receta.getMedicoId()
        );

        existing.setPacienteId(
                receta.getPacienteId()
        );

        existing.setMedicoId(
                receta.getMedicoId()
        );

        existing.setMedicamentos(
                receta.getMedicamentos()
        );

        existing.setFecha(
                receta.getFecha()
        );

        return recetaRepository.save(existing);
    }

    public void delete(Long id) {

        recetaRepository.deleteById(id);
    }

    public List<RecetaDTO> buscarPorPaciente(Long pacienteId) {

        List<Receta> recetas =
                recetaRepository.findByPacienteId(
                        pacienteId
                );

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        List<MedicoDTO> medicos =
                new ArrayList<>();

        for (Receta receta : recetas) {

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            receta.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            receta.getMedicoId()
                    )
            );
        }

        return recetaMapper.toDTOlist(
                recetas,
                pacientes,
                medicos
        );
    }

    public List<RecetaDTO> buscarPorMedicamento(
            String medicamento
    ) {

        List<Receta> recetas =
                recetaRepository.findByMedicamentos(
                        medicamento
                );

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        List<MedicoDTO> medicos =
                new ArrayList<>();

        for (Receta receta : recetas) {

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            receta.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            receta.getMedicoId()
                    )
            );
        }

        return recetaMapper.toDTOlist(
                recetas,
                pacientes,
                medicos
        );
    }

    public List<RecetaDTO> buscarPorFecha(
            LocalDate fecha
    ) {

        List<Receta> recetas =
                recetaRepository.findByFecha(
                        fecha
                );

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        List<MedicoDTO> medicos =
                new ArrayList<>();

        for (Receta receta : recetas) {

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            receta.getPacienteId()
                    )
            );

            medicos.add(
                    medicoFeign.buscarPorId(
                            receta.getMedicoId()
                    )
            );
        }

        return recetaMapper.toDTOlist(
                recetas,
                pacientes,
                medicos
        );
    }
}