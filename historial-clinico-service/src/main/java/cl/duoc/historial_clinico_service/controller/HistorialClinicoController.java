package cl.duoc.historial_clinico_service.controller;

import cl.duoc.historial_clinico_service.dto.HistorialClinicoDTO;
import cl.duoc.historial_clinico_service.exception.ExceptionResponse;
import cl.duoc.historial_clinico_service.model.HistorialClinico;
import cl.duoc.historial_clinico_service.service.HistorialClinicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(
        name = "Historial Clínico",
        description = "Operaciones disponibles para la gestión del historial clínico."
)
@RestController
@RequestMapping("/api/historial-clinico")
@RequiredArgsConstructor
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoService historialClinicoService;

    @Operation(
            summary = "Listar historiales clínicos",
            description = "Obtiene el listado completo de historiales clínicos registrados en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Historiales clínicos listados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = HistorialClinicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<HistorialClinicoDTO> obtenerTodos() {

        return historialClinicoService.obtenerTodos();
    }

    @Operation(
            summary = "Buscar historial clínico por ID",
            description = "Obtiene un historial clínico específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Historial clínico encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HistorialClinico.class)))
    @ApiResponse(responseCode = "404", description = "Historial clínico no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public HistorialClinico obtenerPorId(
            @Parameter(description = "ID del historial clínico", example = "1")
            @PathVariable Long id
    ) {

        return historialClinicoService.obtenerPorId(id);
    }

    @Operation(
            summary = "Crear historial clínico",
            description = "Registra un nuevo historial clínico en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Historial clínico creado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HistorialClinico.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public HistorialClinico crearHistorial(
            @RequestBody HistorialClinico historial
    ) {

        return historialClinicoService.guardarHistorial(historial);
    }

    @Operation(
            summary = "Actualizar historial clínico",
            description = "Actualiza un historial clínico existente."
    )
    @ApiResponse(responseCode = "200", description = "Historial clínico actualizado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HistorialClinico.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Historial clínico no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public HistorialClinico actualizarHistorial(
            @Parameter(description = "ID del historial clínico", example = "1")
            @PathVariable Long id,
            @RequestBody HistorialClinico historial
    ) {

        return historialClinicoService.actualizarHistorial(
                id,
                historial
        );
    }

    @Operation(
            summary = "Eliminar historial clínico",
            description = "Elimina un historial clínico existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Historial clínico eliminado correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Historial clínico no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public void eliminarHistorial(
            @Parameter(description = "ID del historial clínico", example = "1")
            @PathVariable Long id
    ) {

        historialClinicoService.eliminarHistorial(id);
    }

    @Operation(
            summary = "Buscar historiales por paciente",
            description = "Obtiene todos los historiales clínicos asociados a un paciente."
    )
    @ApiResponse(responseCode = "200", description = "Historiales encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = HistorialClinicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/paciente/{pacienteId}")
    public List<HistorialClinicoDTO> buscarPorPaciente(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long pacienteId
    ) {

        return historialClinicoService.buscarPorPaciente(pacienteId);
    }

    @Operation(
            summary = "Buscar historiales por médico",
            description = "Obtiene todos los historiales clínicos asociados a un médico."
    )
    @ApiResponse(responseCode = "200", description = "Historiales encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = HistorialClinicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/medico/{medicoId}")
    public List<HistorialClinicoDTO> buscarPorMedico(
            @Parameter(description = "ID del médico", example = "1")
            @PathVariable Long medicoId
    ) {

        return historialClinicoService.buscarPorMedico(medicoId);
    }

    @Operation(
            summary = "Buscar historiales por diagnóstico",
            description = "Obtiene todos los historiales clínicos que coinciden con un diagnóstico."
    )
    @ApiResponse(responseCode = "200", description = "Historiales encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = HistorialClinicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/diagnostico/{diagnostico}")
    public List<HistorialClinicoDTO> buscarPorDiagnostico(
            @Parameter(description = "Diagnóstico", example = "Hipertensión")
            @PathVariable String diagnostico
    ) {

        return historialClinicoService.buscarPorDiagnostico(diagnostico);
    }

    @Operation(
            summary = "Buscar historiales por fecha",
            description = "Obtiene todos los historiales clínicos registrados en una fecha determinada."
    )
    @ApiResponse(responseCode = "200", description = "Historiales encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = HistorialClinicoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/fecha/{fecha}")
    public List<HistorialClinicoDTO> buscarPorFecha(
            @Parameter(description = "Fecha del historial clínico", example = "2026-06-18")
            @PathVariable LocalDate fecha
    ) {

        return historialClinicoService.buscarPorFecha(fecha);
    }
}