package org.roguesoft.docapp.domain.mapper;

import org.roguesoft.docapp.application.dto.filter.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface FilterMapper<T> {

    PageRequest toPageRequest(Filter filter);

    Specification<T> toSpecification(Filter filter);

}
