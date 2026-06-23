package cl.duoc.pacientes_service.controller;

import cl.duoc.pacientes_service.dto.PacienteDTO;
import cl.duoc.pacientes_service.exception.ExceptionResponse;
import cl.duoc.pacientes_service.mapper.PacienteMapper;
import cl.duoc.pacientes_service.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "Pacientes",
        description = "Operaciones disponibles para la gestión de pacientes."
)
@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    @Autowired
    private PacienteService service;

    @Operation(
            summary = "Listar pacientes",
            description = "Obtiene el listado completo de pacientes registrados en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Pacientes listados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PacienteDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<PacienteDTO> obtenerTodos() {
        return service.getAll();
    }

    @Operation(
            summary = "Buscar paciente por ID",
            description = "Obtiene un paciente específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Paciente encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PacienteDTO.class)))
    @ApiResponse(responseCode = "404", description = "Paciente no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public PacienteDTO obtenerPorId(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long id) {

        return service.getById(id);
    }

    @Operation(
            summary = "Crear paciente",
            description = "Registra un nuevo paciente en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Paciente creado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PacienteDTO.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public PacienteDTO crear(
            @Valid @RequestBody PacienteDTO dto) {

        return service.create(dto);
    }

    @Operation(
            summary = "Actualizar paciente",
            description = "Actualiza la información de un paciente existente."
    )
    @ApiResponse(responseCode = "200", description = "Paciente actualizado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PacienteDTO.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Paciente no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public PacienteDTO actualizar(
            @Parameter(description = "ID del paciente", example = "1")
            @Valid @PathVariable Long id,
            @RequestBody PacienteDTO dto) {

        return service.update(id, dto);
    }

    @Operation(
            summary = "Eliminar paciente",
            description = "Elimina un paciente existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Paciente eliminado correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Paciente no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long id) {

        service.delete(id);
    }

    @Operation(
            summary = "Buscar paciente por RUT",
            description = "Obtiene un paciente según su RUT."
    )
    @ApiResponse(responseCode = "200", description = "Paciente encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PacienteDTO.class)))
    @ApiResponse(responseCode = "404", description = "Paciente no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/rut/{rut}")
    public ResponseEntity<PacienteDTO> buscarPorRut(
            @Parameter(description = "RUT del paciente", example = "12345678-9")
            @PathVariable String rut) {

        return service.buscarPorRut(rut)
                .map(PacienteMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Buscar pacientes por nombre",
            description = "Obtiene todos los pacientes cuyo nombre coincide con el criterio de búsqueda."
    )
    @ApiResponse(responseCode = "200", description = "Pacientes encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PacienteDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/nombre/{nombre}")
    public List<PacienteDTO> buscarPorNombre(
            @Parameter(description = "Nombre del paciente", example = "Juan")
            @PathVariable String nombre) {

        return service.buscarPorNombre(nombre)
                .stream()
                .map(PacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Buscar pacientes por teléfono",
            description = "Obtiene todos los pacientes registrados con un número de teléfono."
    )
    @ApiResponse(responseCode = "200", description = "Pacientes encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PacienteDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/telefono/{telefono}")
    public List<PacienteDTO> buscarPorTelefono(
            @Parameter(description = "Número de teléfono", example = "987654321")
            @PathVariable String telefono) {

        return service.buscarPorTelefono(telefono)
                .stream()
                .map(PacienteMapper::toDTO)
                .collect(Collectors.toList());
    }
}