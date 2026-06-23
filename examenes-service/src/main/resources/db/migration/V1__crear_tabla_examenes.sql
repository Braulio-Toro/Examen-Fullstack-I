CREATE TABLE examenes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    tipo_examen VARCHAR(100) NOT NULL,
    resultado VARCHAR(255) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL
);