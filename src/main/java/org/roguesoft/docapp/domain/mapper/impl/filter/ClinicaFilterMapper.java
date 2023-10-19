package org.roguesoft.docapp.domain.mapper.impl.filter;

import org.roguesoft.docapp.application.dto.filter.ClinicaFilter;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.infrastructure.model.Clinica;
import org.roguesoft.docapp.infrastructure.specification.ClinicaSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClinicaFilterMapper implements FilterMapper<Clinica> {
    @Override
    public PageRequest toPageRequest(Filter filter) {
        return PageRequest.of(filter.getPage(), filter.getSize(),
                Sort.by(Sort.Direction.ASC, "nome"));
    }

    @Override
    public Specification<Clinica> toSpecification(Filter filter) {
        ClinicaFilter clinicaFilter = (ClinicaFilter) filter;

        Specification<Clinica> specification = Specification.where(null);

        if(Objects.nonNull(clinicaFilter.getNome())){
            specification = specification.or(ClinicaSpecification.nome(clinicaFilter.getNome()));
        }

        return specification;
    }
}
