package cl.duoc.citas_service.clients;

import cl.duoc.citas_service.dto.PacienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "PACIENTES-SERVICE")
public interface PacienteFeign {

    @GetMapping("/api/pacientes/{id}")
    PacienteDTO buscarPorId(@PathVariable Long id);

    @GetMapping("/api/pacientes")
    List<PacienteDTO> listarPacientes();
}