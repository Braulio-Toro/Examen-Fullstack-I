package cl.duoc.facturaciones_service.controller;

import cl.duoc.facturaciones_service.dto.FacturacionDTO;
import cl.duoc.facturaciones_service.exception.ExceptionResponse;
import cl.duoc.facturaciones_service.model.Facturacion;
import cl.duoc.facturaciones_service.service.FacturacionService;
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
        name = "Facturaciones",
        description = "Operaciones disponibles para la gestión de facturaciones."
)
@RestController
@RequestMapping("/api/facturaciones")
@RequiredArgsConstructor
public class FacturacionController {

    @Autowired
    private FacturacionService facturacionService;

    @Operation(
            summary = "Listar facturaciones",
            description = "Obtiene el listado completo de facturaciones registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Facturaciones listadas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = FacturacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<FacturacionDTO> obtenerTodos() {

        return facturacionService.findDTOList();
    }

    @Operation(
            summary = "Buscar facturación por ID",
            description = "Obtiene una facturación específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Facturación encontrada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Facturacion.class)))
    @ApiResponse(responseCode = "404", description = "Facturación no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public Facturacion obtenerPorId(
            @Parameter(description = "ID de la facturación", example = "1")
            @PathVariable Long id
    ) {

        return facturacionService.getById(id);
    }

    @Operation(
            summary = "Crear facturación",
            description = "Registra una nueva facturación en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Facturación creada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Facturacion.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public Facturacion crear(
            @RequestBody Facturacion facturacion
    ) {

        return facturacionService.create(
                facturacion
        );
    }

    @Operation(
            summary = "Actualizar facturación",
            description = "Actualiza una facturación existente."
    )
    @ApiResponse(responseCode = "200", description = "Facturación actualizada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Facturacion.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Facturación no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public Facturacion actualizar(
            @Parameter(description = "ID de la facturación", example = "1")
            @PathVariable Long id,
            @RequestBody Facturacion facturacion
    ) {

        return facturacionService.update(
                id,
                facturacion
        );
    }

    @Operation(
            summary = "Eliminar facturación",
            description = "Elimina una facturación existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Facturación eliminada correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Facturación no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la facturación", example = "1")
            @PathVariable Long id
    ) {

        facturacionService.delete(id);
    }

    @Operation(
            summary = "Buscar facturaciones por paciente",
            description = "Obtiene todas las facturaciones asociadas a un paciente."
    )
    @ApiResponse(responseCode = "200", description = "Facturaciones encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = FacturacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/paciente/{pacienteId}")
    public List<FacturacionDTO> buscarPorPaciente(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long pacienteId
    ) {

        return facturacionService.buscarPorPaciente(
                pacienteId
        );
    }

    @Operation(
            summary = "Buscar facturaciones por estado",
            description = "Obtiene todas las facturaciones según su estado de pago."
    )
    @ApiResponse(responseCode = "200", description = "Facturaciones encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = FacturacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/estado/{estadoPago}")
    public List<FacturacionDTO> buscarPorEstado(
            @Parameter(description = "Estado del pago", example = "PAGADO")
            @PathVariable String estadoPago
    ) {

        return facturacionService.buscarPorEstado(
                estadoPago
        );
    }

    @Operation(
            summary = "Buscar facturaciones por fecha",
            description = "Obtiene todas las facturaciones registradas en una fecha determinada."
    )
    @ApiResponse(responseCode = "200", description = "Facturaciones encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = FacturacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/fecha/{fecha}")
    public List<FacturacionDTO> buscarPorFecha(
            @Parameter(description = "Fecha de la facturación", example = "2026-06-18")
            @PathVariable LocalDate fecha
    ) {

        return facturacionService.buscarPorFecha(
                fecha
        );
    }
}