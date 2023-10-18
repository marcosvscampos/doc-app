package org.roguesoft.docapp.domain.mapper.impl;

import org.roguesoft.docapp.application.dto.ClinicaDTO;
import org.roguesoft.docapp.application.dto.ConvenioDTO;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.springframework.stereotype.Component;

@Component
public class ClinicaDomainMapper implements DomainMapper<ClinicaDTO, Clinica> {

    private final DomainMapper<ConvenioDTO, Convenio> convenioDomainMapper;

    public ClinicaDomainMapper(final DomainMapper<ConvenioDTO, Convenio> domainMapper){
        this.convenioDomainMapper = domainMapper;
    }

    @Override
    public Clinica toModel(ClinicaDTO request) {
        Clinica clinica = new Clinica();
        clinica.setNome(request.getNome());
        clinica.setEmail(request.getEmail());
        clinica.setTelefone(request.getTelefone());

        return clinica;
    }

    @Override
    public ClinicaDTO toDto(Clinica model) {
        ConvenioDTO convenioDTO = convenioDomainMapper.toDto(model.getConvenio());
        return ClinicaDTO.clinicaBuilder()
                .id(model.getId())
                .nome(model.getNome())
                .email(model.getEmail())
                .telefone(model.getTelefone())
                .convenio(convenioDTO)
                .build();
    }
}
