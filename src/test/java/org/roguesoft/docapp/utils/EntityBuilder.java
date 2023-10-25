package org.roguesoft.docapp.utils;

public interface EntityBuilder<T>{

    T build(final String... params);

}
