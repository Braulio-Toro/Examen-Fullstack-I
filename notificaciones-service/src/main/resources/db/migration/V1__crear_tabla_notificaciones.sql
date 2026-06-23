CREATE TABLE notificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    fecha_envio DATE NOT NULL
);