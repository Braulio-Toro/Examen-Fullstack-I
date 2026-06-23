package cl.duoc.examenes_service.controller;

import cl.duoc.examenes_service.dto.ExamenDTO;
import cl.duoc.examenes_service.exception.ExceptionResponse;
import cl.duoc.examenes_service.model.Examen;
import cl.duoc.examenes_service.service.ExamenService;
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
        name = "Exámenes",
        description = "Operaciones disponibles para la gestión de exámenes médicos."
)
@RestController
@RequestMapping("/api/examenes")
@RequiredArgsConstructor
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @Operation(
            summary = "Listar exámenes",
            description = "Obtiene el listado completo de exámenes registrados en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Exámenes listados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ExamenDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<ExamenDTO> obtenerTodos() {

        return examenService.findDTOList();
    }

    @Operation(
            summary = "Buscar examen por ID",
            description = "Obtiene un examen específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Examen encontrado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Examen.class)))
    @ApiResponse(responseCode = "404", description = "Examen no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public Examen obtenerPorId(
            @Parameter(description = "ID del examen", example = "1")
            @PathVariable Long id
    ) {

        return examenService.getById(id);
    }

    @Operation(
            summary = "Crear examen",
            description = "Registra un nuevo examen en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Examen creado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Examen.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public Examen crear(
            @RequestBody Examen examen
    ) {

        return examenService.create(examen);
    }

    @Operation(
            summary = "Actualizar examen",
            description = "Actualiza un examen existente."
    )
    @ApiResponse(responseCode = "200", description = "Examen actualizado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Examen.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Examen no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public Examen actualizar(
            @Parameter(description = "ID del examen", example = "1")
            @PathVariable Long id,
            @RequestBody Examen examen
    ) {

        return examenService.update(
                id,
                examen
        );
    }

    @Operation(
            summary = "Eliminar examen",
            description = "Elimina un examen existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Examen eliminado correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Examen no encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del examen", example = "1")
            @PathVariable Long id
    ) {

        examenService.delete(id);
    }

    @Operation(
            summary = "Buscar exámenes por paciente",
            description = "Obtiene todos los exámenes asociados a un paciente."
    )
    @ApiResponse(responseCode = "200", description = "Exámenes encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ExamenDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/paciente/{pacienteId}")
    public List<ExamenDTO> buscarPorPaciente(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long pacienteId
    ) {

        return examenService.buscarPorPaciente(pacienteId);
    }

    @Operation(
            summary = "Buscar exámenes por tipo",
            description = "Obtiene todos los exámenes según su tipo."
    )
    @ApiResponse(responseCode = "200", description = "Exámenes encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ExamenDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/tipo/{tipoExamen}")
    public List<ExamenDTO> buscarPorTipoExamen(
            @Parameter(description = "Tipo de examen", example = "Hemograma")
            @PathVariable String tipoExamen
    ) {

        return examenService.buscarPorTipoExamen(tipoExamen);
    }

    @Operation(
            summary = "Buscar exámenes por fecha",
            description = "Obtiene todos los exámenes realizados en una fecha determinada."
    )
    @ApiResponse(responseCode = "200", description = "Exámenes encontrados correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ExamenDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/fecha/{fecha}")
    public List<ExamenDTO> buscarPorFecha(
            @Parameter(description = "Fecha del examen", example = "2026-06-18")
            @PathVariable LocalDate fecha
    ) {

        return examenService.buscarPorFecha(fecha);
    }
}