package cl.duoc.medicos_service.service;

import cl.duoc.medicos_service.model.Medico;
import cl.duoc.medicos_service.repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService service;

    @Test
    void obtenerPorId_RetornarMedico() {

        Medico medico = new Medico();
        medico.setId(1L);
        medico.setNombre("Dr. House");

        when(medicoRepository.findById(1L))
                .thenReturn(Optional.of(medico));

        Optional<Medico> resultado = service.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Dr. House", resultado.get().getNombre());

        verify(medicoRepository).findById(1L);
    }
}