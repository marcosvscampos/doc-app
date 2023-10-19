package org.roguesoft.docapp.domain.mapper.impl.filter;

import org.roguesoft.docapp.application.dto.filter.ConvenioFilter;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.roguesoft.docapp.domain.mapper.FilterMapper;
import org.roguesoft.docapp.infrastructure.model.Convenio;
import org.roguesoft.docapp.infrastructure.specification.ConvenioSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConvenioFilterMapper implements FilterMapper<Convenio> {


    @Override
    public PageRequest toPageRequest(Filter filter) {
        return PageRequest.of(filter.getPage(), filter.getSize(),
                Sort.by(Sort.Direction.ASC, "cnpj"));
    }

    @Override
    public Specification<Convenio> toSpecification(Filter filter) {
        ConvenioFilter convenioFilter = (ConvenioFilter) filter;
        Specification<Convenio> specification = Specification.where(null);

        if(Objects.nonNull(convenioFilter.getCnpj())) {
            specification = specification.or(ConvenioSpecification.cnpj(convenioFilter.getCnpj()));
        }

        return specification;
    }
}
