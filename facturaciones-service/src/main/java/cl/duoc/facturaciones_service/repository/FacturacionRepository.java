package cl.duoc.facturaciones_service.repository;

import cl.duoc.facturaciones_service.model.Facturacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {

    List<Facturacion> findByPacienteId(Long pacienteId);

    List<Facturacion> findByEstadoPago(String estadoPago);

    List<Facturacion> findByFecha(LocalDate fecha);
}