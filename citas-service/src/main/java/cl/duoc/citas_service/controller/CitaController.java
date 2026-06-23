package cl.duoc.citas_service.controller;

import cl.duoc.citas_service.dto.CitaDTO;
import cl.duoc.citas_service.exception.ExceptionResponse;
import cl.duoc.citas_service.model.Cita;
import cl.duoc.citas_service.service.CitaService;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(
        name = "Citas",
        description = "Operaciones disponibles para la gestión de citas."
)
@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(
            summary = "Listar citas",
            description = "Obtiene el listado completo de citas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Citas listadas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CitaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<CitaDTO> obtenerTodos() {

        return citaService.findDTOList();
    }

    @Operation(
            summary = "Buscar cita por ID",
            description = "Obtiene una cita específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Cita encontrada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cita.class)))
    @ApiResponse(responseCode = "404", description = "Cita no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public Cita obtenerPorId(
            @Parameter(description = "ID de la cita", example = "1")
            @PathVariable Long id
    ) {

        return citaService.getById(id);
    }

    @Operation(
            summary = "Crear cita",
            description = "Registra una nueva cita en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Cita creada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cita.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public Cita crear(
            @Valid @RequestBody Cita cita) {

        return citaService.create(cita);
    }

    @Operation(
            summary = "Actualizar cita",
            description = "Actualiza una cita existente."
    )
    @ApiResponse(responseCode = "200", description = "Cita actualizada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cita.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cita no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public Cita actualizar(
            @Parameter(description = "ID de la cita", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody Cita cita) {

        return citaService.update(id, cita);
    }

    @Operation(
            summary = "Eliminar cita",
            description = "Elimina una cita existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Cita eliminada correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Cita no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la cita", example = "1")
            @PathVariable Long id
    ) {

        citaService.delete(id);
    }

    @Operation(
            summary = "Buscar citas por médico",
            description = "Obtiene todas las citas asociadas a un médico."
    )
    @ApiResponse(responseCode = "200", description = "Citas encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CitaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/medico/{medicoId}")
    public List<CitaDTO> buscarPorMedico(
            @Parameter(description = "ID del médico", example = "1")
            @PathVariable Long medicoId
    ) {

        return citaService.buscarPorMedico(medicoId);
    }

    @Operation(
            summary = "Buscar citas por paciente",
            description = "Obtiene todas las citas asociadas a un paciente."
    )
    @ApiResponse(responseCode = "200", description = "Citas encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CitaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/paciente/{pacienteId}")
    public List<CitaDTO> buscarPorPaciente(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long pacienteId
    ) {

        return citaService.buscarPorPaciente(pacienteId);
    }

    @Operation(
            summary = "Buscar citas por fecha",
            description = "Obtiene todas las citas programadas para una fecha específica."
    )
    @ApiResponse(responseCode = "200", description = "Citas encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CitaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/fecha/{fecha}")
    public List<CitaDTO> buscarPorFecha(
            @Parameter(description = "Fecha de la cita", example = "2026-06-18")
            @PathVariable LocalDate fecha
    ) {

        return citaService.buscarPorFecha(fecha);
    }
}