package cl.duoc.facturaciones_service.mapper;

import cl.duoc.facturaciones_service.dto.FacturacionDTO;
import cl.duoc.facturaciones_service.dto.PacienteDTO;
import cl.duoc.facturaciones_service.model.Facturacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FacturacionMapper {

    public FacturacionDTO toDTO(
            Facturacion facturacion,
            PacienteDTO paciente
    ) {

        return FacturacionDTO.builder()
                .id(facturacion.getId())
                .pacienteNombre(
                        paciente.getNombre()
                )
                .monto(facturacion.getMonto())
                .estadoPago(
                        facturacion.getEstadoPago()
                )
                .fecha(facturacion.getFecha())
                .build();
    }

    public List<FacturacionDTO> toDTOlist(
            List<Facturacion> facturaciones,
            List<PacienteDTO> pacientes
    ) {

        List<FacturacionDTO> lista =
                new ArrayList<>();

        for (int i = 0; i < facturaciones.size(); i++) {

            lista.add(
                    toDTO(
                            facturaciones.get(i),
                            pacientes.get(i)
                    )
            );
        }

        return lista;
    }
}