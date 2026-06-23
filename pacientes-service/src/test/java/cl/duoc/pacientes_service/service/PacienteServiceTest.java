package cl.duoc.pacientes_service.service;

import cl.duoc.pacientes_service.dto.PacienteDTO;
import cl.duoc.pacientes_service.model.Paciente;
import cl.duoc.pacientes_service.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteService service;

    @Test
    void getById_RetornarPacienteDTO() {

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Juan Perez");
        paciente.setRut("11.111.111-1");
        paciente.setTelefono("999999999");
        paciente.setFechaNacimiento(LocalDate.of(1990,1,1));

        when(repository.findById(1L))
                .thenReturn(Optional.of(paciente));

        PacienteDTO resultado = service.getById(1L);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());

        verify(repository).findById(1L);
    }
}