package cl.duoc.notificaciones_service.controller;

import cl.duoc.notificaciones_service.dto.NotificacionDTO;
import cl.duoc.notificaciones_service.exception.ExceptionResponse;
import cl.duoc.notificaciones_service.model.Notificacion;
import cl.duoc.notificaciones_service.service.NotificacionService;
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
        name = "Notificaciones",
        description = "Operaciones disponibles para la gestión de notificaciones."
)
@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(
            summary = "Listar notificaciones",
            description = "Obtiene el listado completo de notificaciones registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Notificaciones listadas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = NotificacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public List<NotificacionDTO> obtenerTodos() {
        return notificacionService.getAll();
    }

    @Operation(
            summary = "Buscar notificación por ID",
            description = "Obtiene una notificación específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Notificación encontrada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Notificacion.class)))
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public Notificacion obtenerPorId(
            @Parameter(description = "ID de la notificación", example = "1")
            @PathVariable Long id) {

        return notificacionService.getById(id);
    }

    @Operation(
            summary = "Crear notificación",
            description = "Registra una nueva notificación en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Notificación creada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Notificacion.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public Notificacion crear(
            @RequestBody Notificacion notificacion) {

        return notificacionService.create(notificacion);
    }

    @Operation(
            summary = "Actualizar notificación",
            description = "Actualiza una notificación existente."
    )
    @ApiResponse(responseCode = "200", description = "Notificación actualizada correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Notificacion.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public Notificacion actualizar(
            @Parameter(description = "ID de la notificación", example = "1")
            @PathVariable Long id,
            @RequestBody Notificacion notificacion) {

        return notificacionService.update(id, notificacion);
    }

    @Operation(
            summary = "Eliminar notificación",
            description = "Elimina una notificación existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Notificación eliminada correctamente",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la notificación", example = "1")
            @PathVariable Long id) {

        notificacionService.delete(id);
    }

    @Operation(
            summary = "Buscar notificaciones por paciente",
            description = "Obtiene todas las notificaciones asociadas a un paciente."
    )
    @ApiResponse(responseCode = "200", description = "Notificaciones encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = NotificacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/paciente/{pacienteId}")
    public List<NotificacionDTO> buscarPorPaciente(
            @Parameter(description = "ID del paciente", example = "1")
            @PathVariable Long pacienteId) {

        return notificacionService.buscarPorPaciente(pacienteId);
    }

    @Operation(
            summary = "Buscar notificaciones por tipo",
            description = "Obtiene todas las notificaciones según su tipo."
    )
    @ApiResponse(responseCode = "200", description = "Notificaciones encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = NotificacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/tipo/{tipo}")
    public List<NotificacionDTO> buscarPorTipo(
            @Parameter(description = "Tipo de notificación", example = "Correo")
            @PathVariable String tipo) {

        return notificacionService.buscarPorTipo(tipo);
    }

    @Operation(
            summary = "Buscar notificaciones por fecha",
            description = "Obtiene todas las notificaciones registradas en una fecha determinada."
    )
    @ApiResponse(responseCode = "200", description = "Notificaciones encontradas correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = NotificacionDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/fecha/{fecha}")
    public List<NotificacionDTO> buscarPorFecha(
            @Parameter(description = "Fecha de la notificación", example = "2026-06-18")
            @PathVariable LocalDate fecha) {

        return notificacionService.buscarPorFecha(fecha);
    }
}