package cl.duoc.citas_service;

import cl.duoc.citas_service.clients.MedicoFeign;
import cl.duoc.citas_service.clients.PacienteFeign;
import cl.duoc.citas_service.dto.MedicoDTO;
import cl.duoc.citas_service.dto.PacienteDTO;
import cl.duoc.citas_service.mapper.CitaMapper;
import cl.duoc.citas_service.model.Cita;
import cl.duoc.citas_service.repository.CitaRepository;
import cl.duoc.citas_service.service.CitaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    private CitaService service;

    @Test
    void GuardarCitaSiPacienteYMedicoExisten() {

        Cita cita = new Cita();
        cita.setPacienteId(1L);
        cita.setMedicoId(1L);

        PacienteDTO paciente = new PacienteDTO();
        paciente.setId(1L);

        MedicoDTO medico = new MedicoDTO();
        medico.setId(1L);

        when(pacienteFeign.buscarPorId(1L))
                .thenReturn(paciente);

        when(medicoFeign.buscarPorId(1L))
                .thenReturn(medico);

        when(citaRepository.save(any(Cita.class)))
                .thenReturn(cita);

        Cita resultado = service.create(cita);

        assertNotNull(resultado);

        verify(pacienteFeign).buscarPorId(1L);
        verify(medicoFeign).buscarPorId(1L);
        verify(citaRepository).save(cita);
    }
}