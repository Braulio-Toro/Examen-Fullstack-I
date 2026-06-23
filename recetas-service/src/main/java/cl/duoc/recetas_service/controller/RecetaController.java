package cl.duoc.recetas_service.controller;

import cl.duoc.recetas_service.dto.RecetaDTO;
import cl.duoc.recetas_service.exception.ExceptionResponse;
import cl.duoc.recetas_service.model.Receta;
import cl.duoc.recetas_service.service.RecetaService;
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
        name = "Recetas",
        description = "Operaciones disponibles para la gestión de recetas médicas."
)
@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @Operation(
            summary = "Listar recetas",
            description = "Obtiene el listado completo de recetas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Recetas listadas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RecetaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<RecetaDTO> obtenerTodos() {

        return recetaService.findDTOList();
    }

    @Operation(
            summary = "Buscar receta por ID",
            description = "Obtiene una receta específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Receta encontrada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Receta.class)))
    @ApiResponse(responseCode = "404", description = "Receta no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public Receta obtenerPorId(
            @Parameter(description = "ID de la receta", example = "1")
            @PathVariable Long id
    ) {

        return recetaService.getById(id);
    }

    @Operation(
            summary = "Crear receta",
            description = "Registra una nueva receta médica en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Receta creada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Receta.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public Receta crear(
            @RequestBody Receta receta
    ) {

        return recetaService.create(receta);
    }

    @Operation(
            summary = "Actualizar receta",
            description = "Actualiza una receta existente."
    )
    @ApiResponse(responseCode = "200", description = "Receta actualizada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Receta.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Receta no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public Receta actualizar(
            @Parameter(description = "ID de la receta", example = "1")
            @PathVariable Long id,
            @RequestBody Receta receta
    ) {

        return recetaService.update(
                id,
                receta
        );
    }

    @Operation(
            summary = "Eliminar receta",
            description = "Elimina una receta existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Receta eliminada correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Receta no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la receta", example = "1")
            @PathVariable Long id
    ) {

        recetaService.delete(id);
    }

    @Operation(
            summary = "Buscar recetas por paciente",
            description = "Obtiene todas las recetas asociadas a un paciente."
    )
    @ApiResponse(responseCode = "200", description = "Recetas encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RecetaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/paciente/{pacienteId}")
    public List<RecetaDTO> buscarPorPaciente(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long pacienteId
    ) {

        return recetaService.buscarPorPaciente(
                pacienteId
        );
    }

    @Operation(
            summary = "Buscar recetas por medicamento",
            description = "Obtiene todas las recetas que contienen un medicamento específico."
    )
    @ApiResponse(responseCode = "200", description = "Recetas encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RecetaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/medicamento/{medicamento}")
    public List<RecetaDTO> buscarPorMedicamento(
            @Parameter(description = "Nombre del medicamento", example = "Paracetamol")
            @PathVariable String medicamento
    ) {

        return recetaService.buscarPorMedicamento(
                medicamento
        );
    }

    @Operation(
            summary = "Buscar recetas por fecha",
            description = "Obtiene todas las recetas emitidas en una fecha específica."
    )
    @ApiResponse(responseCode = "200", description = "Recetas encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RecetaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/fecha/{fecha}")
    public List<RecetaDTO> buscarPorFecha(
            @Parameter(description = "Fecha de emisión", example = "2026-06-18")
            @PathVariable LocalDate fecha
    ) {

        return recetaService.buscarPorFecha(
                fecha
        );
    }
}