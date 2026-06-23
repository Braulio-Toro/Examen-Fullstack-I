package cl.duoc.citas_service.clients;

import cl.duoc.citas_service.dto.MedicoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "MEDICOS-SERVICE")
public interface MedicoFeign {

    @GetMapping("/api/medicos/{id}")
    MedicoDTO buscarPorId(@PathVariable Long id);

    @GetMapping("/api/medicos")
    List<MedicoDTO> listarMedicos();
}