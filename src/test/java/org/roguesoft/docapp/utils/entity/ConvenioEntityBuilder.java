package org.roguesoft.docapp.utils.entity;

import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.roguesoft.docapp.infrastructure.model.Individuo;
import org.roguesoft.docapp.utils.EntityBuilder;

public class ConvenioEntityBuilder implements EntityBuilder<Convenio> {

    public final static String CONVENIO_ID = "IND-123456789-CNV";

    @Override
    public Convenio build(String... params) {
        Individuo individuo = Individuo.builder()
                .nome("Convenio Junit Test")
                .build();

        return Convenio.builder()
                .id(CONVENIO_ID)
                .cnpj(params[0])
                .individuo(individuo)
                .build();
    }
}
