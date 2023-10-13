package org.roguesoft.docapp.domain.mapper;

public interface DomainMapper<T, X> {

    X toModel(T t);

    T toDto(X x);
}
