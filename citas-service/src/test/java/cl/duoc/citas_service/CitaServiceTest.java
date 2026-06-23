package cl.duoc.citas_service;

import cl.duoc.citas_service.clients.MedicoFeign;
import cl.duoc.citas_service.clients.PacienteFeign;
import cl.duoc.citas_service.dto.MedicoDTO;
import cl.duoc.citas_service.dto.PacienteDTO;
import cl.duoc.citas_service.mapper.CitaMapper;
import cl.duoc.citas_service.model.Cita;
import cl.duoc.citas_service.repository.CitaRepository;
import cl.duoc.citas_service.service.CitaService;

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
@DisplayName("Pruebas unitarias para CitaService")
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CitaMapper citaMapper;

    @Mock
    private PacienteFeign pacienteFeign;

    @Mock
    private MedicoFeign medicoFeign;

    @InjectMocks
    private CitaService citaService;

    private Cita cita;
    private PacienteDTO paciente;
    private MedicoDTO medico;

    @BeforeEach
    void setUp() {

        cita = new Cita();
        cita.setId(1L);
        cita.setPacienteId(1L);
        cita.setMedicoId(1L);

        paciente = new PacienteDTO();
        paciente.setId(1L);

        medico = new MedicoDTO();
        medico.setId(1L);
    }

    @Test
    @DisplayName("Debe guardar una cita cuando el paciente y médico existen")
    void create_cuandoPacienteYMedicoExisten_deberiaGuardarCita() {

        when(pacienteFeign.buscarPorId(1L))
                .thenReturn(paciente);

        when(medicoFeign.buscarPorId(1L))
                .thenReturn(medico);

        when(citaRepository.save(any(Cita.class)))
                .thenReturn(cita);

        Cita resultado = citaService.create(cita);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getPacienteId());
        assertEquals(1L, resultado.getMedicoId());

        verify(pacienteFeign).buscarPorId(1L);
        verify(medicoFeign).buscarPorId(1L);
        verify(citaRepository).save(cita);
    }

    @Test
    @DisplayName("No debe guardar una cita cuando el paciente no existe")
    void create_cuandoPacienteNoExiste_deberiaLanzarExcepcion() {

        when(pacienteFeign.buscarPorId(1L))
                .thenReturn(null);

        Exception exception = assertThrows(
                RuntimeException.class,
                () -> citaService.create(cita)
        );

        assertNotNull(exception);

        verify(pacienteFeign).buscarPorId(1L);
        verify(medicoFeign, never()).buscarPorId(anyLong());
        verify(citaRepository, never()).save(any(Cita.class));
    }

    @Test
    @DisplayName("No debe guardar una cita cuando el médico no existe")
    void create_cuandoMedicoNoExiste_deberiaLanzarExcepcion() {

        when(pacienteFeign.buscarPorId(1L))
                .thenReturn(paciente);

        when(medicoFeign.buscarPorId(1L))
                .thenReturn(null);

        Exception exception = assertThrows(
                RuntimeException.class,
                () -> citaService.create(cita)
        );

        assertNotNull(exception);

        verify(pacienteFeign).buscarPorId(1L);
        verify(medicoFeign).buscarPorId(1L);
        verify(citaRepository, never()).save(any(Cita.class));
    }
}