INSERT INTO historial_clinico (
    paciente_id,
    medico_id,
    diagnostico,
    fecha
)
VALUES
    (
        1,
        2,
        'Paciente presenta cuadro de amigdalitis aguda. Se receta antibiótico (Amoxicilina) y reposo por 3 días.',
        '2026-05-01'
    ),
    (
        3,
        1,
        'Control de rutina. Presión arterial normal. Se recomiendan exámenes de sangre preventivos (Perfil lipídico y glicemia).',
        '2026-05-03'
    ),
    (
        2,
        3,
        'Dolor abdominal agudo en la fosa ilíaca derecha. Posible apendicitis, se deriva a urgencias para ecografía de urgencia.',
        '2026-05-05'
    ),
    (
        1,
        2,
        'Control post-tratamiento de amigdalitis. El paciente presenta mejoría total y no reporta molestias adicionales.',
        '2026-05-10'
    );