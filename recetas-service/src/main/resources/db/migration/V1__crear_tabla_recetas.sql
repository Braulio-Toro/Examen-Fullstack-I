CREATE TABLE recetas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    medicamentos VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL
);