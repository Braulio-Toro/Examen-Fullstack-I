package cl.duoc.pacientes_service.repository;

import cl.duoc.pacientes_service.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Optional<Paciente> findByRut(String rut);

    List<Paciente> findByNombreContaining(String nombre);

    List<Paciente> findByTelefono(String telefono);
}
