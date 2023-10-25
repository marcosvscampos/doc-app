package org.roguesoft.docapp.utils.entity;

import org.roguesoft.docapp.infrastructure.model.Feriado;
import org.roguesoft.docapp.utils.EntityBuilder;

import java.util.Optional;

public class FeriadoEntityBuilder implements EntityBuilder<Optional<Feriado>> {

    @Override
    public Optional<Feriado> build(String... params) {
        Feriado feriado = Feriado.builder()
                .id(100)
                .dia(Integer.parseInt(params[0]))
                .mes(Integer.parseInt(params[1]))
                .description("Feriado de testes")
                .build();
        return Optional.of(feriado);
    }
}
