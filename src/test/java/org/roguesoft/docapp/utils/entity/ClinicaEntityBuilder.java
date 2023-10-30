package org.roguesoft.docapp.utils.entity;

import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.roguesoft.docapp.utils.EntityBuilder;

public class ClinicaEntityBuilder implements EntityBuilder<Clinica> {

    public static final String CLINICA_ID = "CLN-123456789-TRX";

    @Override
    public Clinica build(String... params) {
        return Clinica.builder()
                .id(CLINICA_ID)
                .nome(params[0])
                .email(params[1])
                .telefone(params[2])
                .build();
    }
}
