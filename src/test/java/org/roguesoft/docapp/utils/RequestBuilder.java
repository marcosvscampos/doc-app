package org.roguesoft.docapp.utils;

public interface RequestBuilder<T> {

     T buildValid(final String... params);
}
