package cl.duoc.historial_clinico_service.clients;

import cl.duoc.historial_clinico_service.dto.MedicoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MEDICOS-SERVICE")
public interface MedicoFeign {

    @GetMapping("/api/medicos/{id}")
    MedicoDTO buscarPorId(
            @PathVariable Long id
    );
}