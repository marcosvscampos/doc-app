package org.roguesoft.docapp.domain.mapper.impl.entity;

import org.roguesoft.docapp.application.dto.ConvenioDTO;
import org.roguesoft.docapp.application.dto.IndividuoDTO;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.roguesoft.docapp.infrastructure.model.Individuo;
import org.springframework.stereotype.Component;

@Component
public class ConvenioDomainMapper implements DomainMapper<ConvenioDTO, Convenio> {

    private final DomainMapper<IndividuoDTO, Individuo> individuoDomainMapper;

    public ConvenioDomainMapper(DomainMapper<IndividuoDTO, Individuo> domainMapper) {
        this.individuoDomainMapper = domainMapper;
    }

    @Override
    public Convenio toModel(ConvenioDTO request) {
        Convenio convenio = new Convenio();
        convenio.setCnpj(request.getCnpj());
        convenio.setIndividuo(individuoDomainMapper.toModel(request));
        return convenio;
    }

    @Override
    public ConvenioDTO toDto(Convenio model) {
        return ConvenioDTO.convenioBuilder()
                .id(model.getId())
                .cnpj(model.getCnpj())
                .individuo(individuoDomainMapper.toDto(model.getIndividuo()))
                .build();
    }
}
