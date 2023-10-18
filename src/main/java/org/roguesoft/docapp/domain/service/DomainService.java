package org.roguesoft.docapp.domain.service;

import org.roguesoft.docapp.application.dto.ResponseDTO;
import org.roguesoft.docapp.application.dto.filter.Filter;
import org.springframework.data.domain.Page;

public interface DomainService<T> {

    ResponseDTO create(T t);

    T findById(final String id);

    Page<T> findAll(final Filter filter);
}
