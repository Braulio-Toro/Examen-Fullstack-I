package cl.duoc.facturaciones_service;

import cl.duoc.facturaciones_service.clients.PacienteFeign;
import cl.duoc.facturaciones_service.dto.PacienteDTO;
import cl.duoc.facturaciones_service.mapper.FacturacionMapper;
import cl.duoc.facturaciones_service.model.Facturacion;
import cl.duoc.facturaciones_service.repository.FacturacionRepository;
import cl.duoc.facturaciones_service.service.FacturacionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para FacturacionService")
class FacturacionServiceTest {

    @Mock
    private FacturacionRepository facturacionRepository;

    @Mock
    private FacturacionMapper facturacionMapper;

    @Mock
    private PacienteFeign pacienteFeign;

    @InjectMocks
    private FacturacionService facturacionService;

    private Facturacion facturacion;
    private PacienteDTO paciente;

    @BeforeEach
    void setUp() {

        facturacion = new Facturacion();
        facturacion.setId(1L);
        facturacion.setPacienteId(1L);

        paciente = new PacienteDTO();
        paciente.setId(1L);
    }

    @Test
    @DisplayName("Debe guardar una facturación cuando el paciente existe")
    void create_cuandoPacienteExiste_deberiaGuardarFacturacion() {

        when(pacienteFeign.buscarPorId(1L))
                .thenReturn(paciente);

        when(facturacionRepository.save(any(Facturacion.class)))
                .thenReturn(facturacion);

        Facturacion resultado = facturacionService.create(facturacion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getPacienteId());

        verify(pacienteFeign).buscarPorId(1L);
        verify(facturacionRepository).save(facturacion);
    }

    @Test
    @DisplayName("No debe guardar una facturación cuando el paciente no existe")
    void create_cuandoPacienteNoExiste_deberiaLanzarExcepcion() {

        when(pacienteFeign.buscarPorId(1L))
                .thenReturn(null);

        Exception exception = assertThrows(
                RuntimeException.class,
                () -> facturacionService.create(facturacion)
        );

        assertNotNull(exception);

        verify(pacienteFeign).buscarPorId(1L);
        verify(facturacionRepository, never()).save(any(Facturacion.class));
    }
}