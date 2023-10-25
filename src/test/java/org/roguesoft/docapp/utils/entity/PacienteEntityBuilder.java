package org.roguesoft.docapp.utils.entity;

import org.roguesoft.docapp.infrastructure.model.Individuo;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.utils.DateParser;
import org.roguesoft.docapp.utils.EntityBuilder;

import java.util.Date;
import java.util.Optional;

public class PacienteEntityBuilder implements EntityBuilder<Optional<Paciente>> {

    private final static String PACIENTE_ID = "IND-123456789-PAC";

    @Override
    public Optional<Paciente> build(String... params) {
        Date dataNascimento = DateParser.parseData(params[0]);

        Individuo individuo = Individuo.builder()
                .id(PACIENTE_ID)
                .nome("Josiane Santos")
                .build();

        Paciente paciente = Paciente.builder()
                .id(PACIENTE_ID)
                .cpf("36542871827")
                .rg("561872281")
                .dataNascimento(dataNascimento)
                .individuo(individuo)
                .build();
        return Optional.of(paciente);
    }
}
