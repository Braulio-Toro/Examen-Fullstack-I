CREATE TABLE facturaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    monto INT NOT NULL,
    estado_pago VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL
);