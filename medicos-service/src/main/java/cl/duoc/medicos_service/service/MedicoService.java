package cl.duoc.medicos_service.service;

import cl.duoc.medicos_service.model.Medico;
import cl.duoc.medicos_service.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> obtenerTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obtenerPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void eliminarMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return medicoRepository.findByEspecialidad(especialidad);
    }

    public List<Medico> buscarPorNombre(String nombre) {
        return medicoRepository.findByNombreContaining(nombre);
    }

    public Optional<Medico> buscarPorEmail(String email) {
        return medicoRepository.findByEmail(email);
    }
}