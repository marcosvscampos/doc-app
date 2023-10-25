package org.roguesoft.docapp.utils.entity;

import org.roguesoft.docapp.infrastructure.model.ConsultaMedica;
import org.roguesoft.docapp.utils.DateParser;
import org.roguesoft.docapp.utils.EntityBuilder;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ConsultaMedicaEntityBuilder implements EntityBuilder<List<ConsultaMedica>> {

    private static final String CONSULTA_MEDICA_ID = "CSN-123456789-TRX";

    @Override
    public List<ConsultaMedica> build(String... params) {
        ConsultaMedica cm = ConsultaMedica.builder()
                .id(CONSULTA_MEDICA_ID)
                .data(DateParser.parseData(params[0]))
                .horario(DateParser.parseHorario(params[1]))
                .valor(BigDecimal.ZERO)
                .status("PENDENTE")
                .build();

        return Arrays.asList(cm);
    }


}
