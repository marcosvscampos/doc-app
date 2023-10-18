package org.roguesoft.docapp.domain.mapper.impl.filter;

import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.application.dto.filter.PacienteFilter;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.infrastructure.model.Paciente;
import org.roguesoft.docapp.infrastructure.specification.PacienteSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PacienteFilterMapper implements FilterMapper<Paciente> {

    @Override
    public PageRequest toPageRequest(Filter filter) {
        return PageRequest.of(filter.getPage(), filter.getSize(),
                Sort.by(Sort.Direction.ASC, "cpf"));
    }

    @Override
    public Specification<Paciente> toSpecification(Filter filter) {
        PacienteFilter pacienteFilter = (PacienteFilter) filter;

        Specification<Paciente> specification = Specification.where(null);

        if(Objects.nonNull(pacienteFilter.getCpf())){
            specification = specification.or(PacienteSpecification.cpf(pacienteFilter.getCpf()));
        }

        if(Objects.nonNull(pacienteFilter.getRg())) {
            specification = specification.or(PacienteSpecification.rg(pacienteFilter.getRg()));
        }

        return specification;
    }
}
