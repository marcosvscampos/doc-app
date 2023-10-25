package org.roguesoft.docapp.utils;

import java.util.List;

public interface EntityBuilder<T>{

    List<T> build(final String... params);

}
