package org.roguesoft.docapp.domain.service;

import org.roguesoft.docapp.application.dto.ResponseDTO;

import java.util.List;

public interface DomainService<T> {

    ResponseDTO create(T t);

    T findById(final String id);

    List<T> findAll();
}
