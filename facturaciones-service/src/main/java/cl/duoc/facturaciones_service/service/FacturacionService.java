package cl.duoc.facturaciones_service.service;

import cl.duoc.facturaciones_service.clients.PacienteFeign;
import cl.duoc.facturaciones_service.dto.FacturacionDTO;
import cl.duoc.facturaciones_service.dto.PacienteDTO;
import cl.duoc.facturaciones_service.mapper.FacturacionMapper;
import cl.duoc.facturaciones_service.model.Facturacion;
import cl.duoc.facturaciones_service.repository.FacturacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturacionService {

    @Autowired
    private FacturacionMapper facturacionMapper;

    @Autowired
    private FacturacionRepository facturacionRepository;

    @Autowired
    private PacienteFeign pacienteFeign;

    public List<FacturacionDTO> findDTOList(){

        List<Facturacion> facturaciones =
                facturacionRepository.findAll();

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Facturacion facturacion : facturaciones){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            facturacion.getPacienteId()
                    )
            );
        }

        return facturacionMapper.toDTOlist(
                facturaciones,
                pacientes
        );
    }

    public Facturacion getById(Long id) {

        return facturacionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Facturación no encontrada"));
    }

    public Facturacion create(Facturacion facturacion) {

        PacienteDTO paciente =
                pacienteFeign.buscarPorId(
                        facturacion.getPacienteId()
                );

        return facturacionRepository.save(facturacion);
    }

    public Facturacion update(Long id, Facturacion facturacion) {

        Facturacion existing = getById(id);

        PacienteDTO paciente =
                pacienteFeign.buscarPorId(
                        facturacion.getPacienteId()
                );

        existing.setPacienteId(facturacion.getPacienteId());
        existing.setMonto(facturacion.getMonto());
        existing.setEstadoPago(facturacion.getEstadoPago());
        existing.setFecha(facturacion.getFecha());

        return facturacionRepository.save(existing);
    }

    public void delete(Long id) {

        facturacionRepository.deleteById(id);
    }

    public List<FacturacionDTO> buscarPorPaciente(Long pacienteId) {

        List<Facturacion> facturaciones =
                facturacionRepository.findByPacienteId(pacienteId);

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Facturacion facturacion : facturaciones){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            facturacion.getPacienteId()
                    )
            );
        }

        return facturacionMapper.toDTOlist(
                facturaciones,
                pacientes
        );
    }

    public List<FacturacionDTO> buscarPorEstado(String estadoPago) {

        List<Facturacion> facturaciones =
                facturacionRepository.findByEstadoPago(estadoPago);

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Facturacion facturacion : facturaciones){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            facturacion.getPacienteId()
                    )
            );
        }

        return facturacionMapper.toDTOlist(
                facturaciones,
                pacientes
        );
    }

    public List<FacturacionDTO> buscarPorFecha(LocalDate fecha) {

        List<Facturacion> facturaciones =
                facturacionRepository.findByFecha(fecha);

        List<PacienteDTO> pacientes =
                new ArrayList<>();

        for (Facturacion facturacion : facturaciones){

            pacientes.add(
                    pacienteFeign.buscarPorId(
                            facturacion.getPacienteId()
                    )
            );
        }

        return facturacionMapper.toDTOlist(
                facturaciones,
                pacientes
        );
    }
}