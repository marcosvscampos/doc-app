package org.roguesoft.docapp.domain.mapper.impl;

import org.roguesoft.docapp.application.dto.IndividuoDTO;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.infrastructure.model.Individuo;
import org.springframework.stereotype.Component;

@Component
public class IndividuoDomainMapper implements DomainMapper<IndividuoDTO, Individuo> {

    @Override
    public Individuo toModel(final IndividuoDTO request) {
        Individuo individuo = new Individuo();
        individuo.setNome(request.getNome());
        individuo.setTelefone(request.getTelefone());
        EnderecoDomainMapper.toModel(individuo, request.getEndereco());
        return individuo;
    }

    @Override
    public IndividuoDTO toDto(final Individuo model){
        IndividuoDTO dto = new IndividuoDTO();
        dto.setNome(model.getNome());
        dto.setTelefone(model.getTelefone());
        EnderecoDomainMapper.toDto(dto, model);
        return dto;
    }

}
