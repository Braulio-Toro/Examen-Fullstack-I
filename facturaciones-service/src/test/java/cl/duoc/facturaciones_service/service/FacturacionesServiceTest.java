package cl.duoc.facturaciones_service.service;

import cl.duoc.facturaciones_service.clients.PacienteFeign;
import cl.duoc.facturaciones_service.dto.PacienteDTO;
import cl.duoc.facturaciones_service.mapper.FacturacionMapper;
import cl.duoc.facturaciones_service.model.Facturacion;
import cl.duoc.facturaciones_service.repository.FacturacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturacionServiceTest {

    @Mock
    private FacturacionRepository facturacionRepository;

    @Mock
    private FacturacionMapper facturacionMapper;

    @Mock
    private PacienteFeign pacienteFeign;

    @InjectMocks
    private FacturacionService service;

    @Test
    void GuardarFacturacion() {

        Facturacion facturacion = new Facturacion();
        facturacion.setPacienteId(1L);

        PacienteDTO paciente = new PacienteDTO();
        paciente.setId(1L);

        when(pacienteFeign.buscarPorId(1L))
                .thenReturn(paciente);

        when(facturacionRepository.save(any(Facturacion.class)))
                .thenReturn(facturacion);

        Facturacion resultado = service.create(facturacion);

        assertNotNull(resultado);

        verify(pacienteFeign).buscarPorId(1L);
        verify(facturacionRepository).save(facturacion);
    }
}