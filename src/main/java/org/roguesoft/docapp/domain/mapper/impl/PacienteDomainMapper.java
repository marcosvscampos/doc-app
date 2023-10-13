package org.roguesoft.docapp.domain.mapper.impl;

import org.roguesoft.docapp.application.dto.IndividuoDTO;
import org.roguesoft.docapp.application.dto.PacienteDTO;
import org.roguesoft.docapp.domain.mapper.DomainMapper;
import org.roguesoft.docapp.infrastructure.model.Individuo;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteDomainMapper implements DomainMapper<PacienteDTO, Paciente> {

    private final DomainMapper<IndividuoDTO, Individuo> individuoDomainMapper;

    public PacienteDomainMapper(final  DomainMapper<IndividuoDTO, Individuo> domainMapper) {
        this.individuoDomainMapper = domainMapper;
    }

    public Paciente toModel(PacienteDTO request){
        Paciente paciente = new Paciente();
        paciente.setCpf(request.getCpf());
        paciente.setRg(request.getRg());
        paciente.setIndividuo(individuoDomainMapper.toModel(request));
        return paciente;
    }

    public PacienteDTO toDto(Paciente model){
        return PacienteDTO.pacienteBuilder()
                .id(model.getId())
                .cpf(model.getCpf())
                .rg(model.getRg())
                .individuo(individuoDomainMapper.toDto(model.getIndividuo()))
                .build();
    }

}
