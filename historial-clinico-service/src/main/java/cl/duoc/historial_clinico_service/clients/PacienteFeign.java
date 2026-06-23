package cl.duoc.historial_clinico_service.clients;

import cl.duoc.historial_clinico_service.dto.PacienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PACIENTES-SERVICE")
public interface PacienteFeign {

    @GetMapping("/api/pacientes/{id}")
    PacienteDTO buscarPorId(
            @PathVariable Long id
    );
}