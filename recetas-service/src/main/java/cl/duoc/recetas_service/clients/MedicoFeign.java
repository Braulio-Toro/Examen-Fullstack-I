package cl.duoc.recetas_service.clients;

import cl.duoc.recetas_service.dto.MedicoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "medicos-service")
public interface MedicoFeign {

    @GetMapping("/api/medicos/{id}")
    MedicoDTO buscarPorId(@PathVariable Long id);

    @GetMapping("/api/medicos")
    List<MedicoDTO> listarMedicos();
}