package org.roguesoft.docapp.domain.mapper.impl.filter;

import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.application.dto.filter.MedicoFilter;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.infrastructure.model.Medico;
import org.roguesoft.docapp.infrastructure.specification.MedicoSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MedicoFilterMapper implements FilterMapper<Medico> {
    @Override
    public PageRequest toPageRequest(Filter filter) {
        return PageRequest.of(filter.getPage(), filter.getSize(),
                Sort.by(Sort.Direction.ASC, "nome"));
    }

    @Override
    public Specification<Medico> toSpecification(Filter filter) {
        MedicoFilter medicoFilter = (MedicoFilter) filter;

        Specification<Medico> specification = Specification.where(null);

        if(Objects.nonNull(medicoFilter.getNome())){
            specification = specification.or(MedicoSpecification.nome(medicoFilter.getNome()));
        }

        if(Objects.nonNull(medicoFilter.getEspecialidade())) {
            specification = specification.or(MedicoSpecification.especialidade(medicoFilter.getEspecialidade()));
        }

        return specification;
    }
}
