package cl.duoc.pacientes_service.service;

import cl.duoc.pacientes_service.dto.PacienteDTO;
import cl.duoc.pacientes_service.mapper.PacienteMapper;
import cl.duoc.pacientes_service.model.Paciente;
import cl.duoc.pacientes_service.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<PacienteDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(PacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO getById(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return PacienteMapper.toDTO(paciente);
    }

    public PacienteDTO create(PacienteDTO dto) {
        Paciente paciente = PacienteMapper.toEntity(dto);
        return PacienteMapper.toDTO(repository.save(paciente));
    }

    public PacienteDTO update(Long id, PacienteDTO dto) {
        Paciente existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        existing.setNombre(dto.getNombre());
        existing.setRut(dto.getRut());
        existing.setFechaNacimiento(dto.getFechaNacimiento());
        existing.setTelefono(dto.getTelefono());

        return PacienteMapper.toDTO(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Paciente> buscarPorRut(String rut) {
        return repository.findByRut(rut);
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    public List<Paciente> buscarPorTelefono(String telefono) {
        return repository.findByTelefono(telefono);
    }
}