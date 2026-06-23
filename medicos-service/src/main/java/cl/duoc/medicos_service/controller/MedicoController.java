package cl.duoc.medicos_service.controller;

import cl.duoc.medicos_service.dto.MedicoDTO;
import cl.duoc.medicos_service.exception.ExceptionResponse;
import cl.duoc.medicos_service.mapper.MedicoMapper;
import cl.duoc.medicos_service.model.Medico;
import cl.duoc.medicos_service.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "Médicos",
        description = "Operaciones disponibles para la gestión de médicos."
)
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoMapper medicoMapper;

    @Operation(
            summary = "Listar médicos",
            description = "Obtiene el listado completo de médicos registrados en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Médicos listados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MedicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<MedicoDTO> listarMedicos() {
        return medicoService.obtenerTodos()
                .stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Buscar médico por ID",
            description = "Obtiene un médico específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Médico encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MedicoDTO.class)))
    @ApiResponse(responseCode = "404", description = "Médico no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> obtenerMedico(
            @Parameter(description = "ID del médico", example = "1")
            @PathVariable Long id) {

        return medicoService.obtenerPorId(id)
                .map(medicoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Crear médico",
            description = "Registra un nuevo médico en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Médico creado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MedicoDTO.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public MedicoDTO crearMedico(
            @Valid @RequestBody MedicoDTO medicoDTO) {

        Medico medico = medicoMapper.toEntity(medicoDTO);
        Medico medicoGuardado = medicoService.guardarMedico(medico);
        return medicoMapper.toDTO(medicoGuardado);
    }

    @Operation(
            summary = "Actualizar médico",
            description = "Actualiza la información de un médico existente."
    )
    @ApiResponse(responseCode = "200", description = "Médico actualizado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MedicoDTO.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Médico no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> actualizarMedico(
            @Parameter(description = "ID del médico", example = "1")
            @Valid @PathVariable Long id,
            @RequestBody MedicoDTO medicoDTO) {

        return medicoService.obtenerPorId(id)
                .map(medicoExistente -> {
                    medicoExistente.setNombre(medicoDTO.getNombre());
                    medicoExistente.setEspecialidad(medicoDTO.getEspecialidad());
                    medicoExistente.setTelefono(medicoDTO.getTelefono());
                    medicoExistente.setEmail(medicoDTO.getEmail());

                    Medico actualizado = medicoService.guardarMedico(medicoExistente);
                    return ResponseEntity.ok(medicoMapper.toDTO(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Eliminar médico",
            description = "Elimina un médico existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Médico eliminado correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Médico no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(
            @Parameter(description = "ID del médico", example = "1")
            @PathVariable Long id) {

        if (medicoService.obtenerPorId(id).isPresent()) {
            medicoService.eliminarMedico(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Buscar médicos por especialidad",
            description = "Obtiene todos los médicos que pertenecen a una especialidad."
    )
    @ApiResponse(responseCode = "200", description = "Médicos encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MedicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/especialidad/{especialidad}")
    public List<MedicoDTO> buscarPorEspecialidad(
            @Parameter(description = "Especialidad del médico", example = "Cardiología")
            @PathVariable String especialidad) {

        return medicoService.buscarPorEspecialidad(especialidad)
                .stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Buscar médicos por nombre",
            description = "Obtiene todos los médicos cuyo nombre coincide con el criterio de búsqueda."
    )
    @ApiResponse(responseCode = "200", description = "Médicos encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MedicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/nombre/{nombre}")
    public List<MedicoDTO> buscarPorNombre(
            @Parameter(description = "Nombre del médico", example = "Juan")
            @PathVariable String nombre) {

        return medicoService.buscarPorNombre(nombre)
                .stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Buscar médico por correo electrónico",
            description = "Obtiene un médico según su correo electrónico."
    )
    @ApiResponse(responseCode = "200", description = "Médico encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MedicoDTO.class)))
    @ApiResponse(responseCode = "404", description = "Médico no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/email/{email}")
    public ResponseEntity<MedicoDTO> buscarPorEmail(
            @Parameter(description = "Correo electrónico del médico", example = "medico@auramed.cl")
            @PathVariable String email) {

        return medicoService.buscarPorEmail(email)
                .map(medicoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}