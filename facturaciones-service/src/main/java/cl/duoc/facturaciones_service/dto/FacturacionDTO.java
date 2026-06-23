package cl.duoc.facturaciones_service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturacionDTO {

    private Long id;

    private String pacienteNombre;

    private Integer monto;

    private String estadoPago;

    private LocalDate fecha;
}